package tfg.romerias.ticket.service;
import java.time.LocalDateTime;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tfg.romerias.exceptions.BadRequestException;
import tfg.romerias.floats.model.Floats;
import tfg.romerias.floats.repository.FloatsRepository;
import tfg.romerias.floats.service.FloatsService;
import tfg.romerias.pilgrimage.model.Pilgrimage;
import tfg.romerias.pilgrimage.service.PilgrimageService;
import tfg.romerias.ticket.model.Ticket;
import tfg.romerias.ticket.repository.TicketRepository;
import tfg.romerias.user.model.User;
import tfg.romerias.user.service.UserService;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TicketService implements ITicketService{

    private final TicketRepository ticketRepository;
    private final FloatsService floatsService;
    private final PilgrimageService pilgrimageService;
    private final UserService userService;

    public TicketService(TicketRepository ticketRepository, FloatsService floatsService, PilgrimageService pilgrimageService, UserService userService) {
        this.ticketRepository = ticketRepository;
        this.floatsService = floatsService;
        this.pilgrimageService = pilgrimageService;
        this.userService = userService;
    }

    @Override
    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> getTicketsByFloat(Integer idFloats) {
        return ticketRepository.findTicketsByFloat(idFloats);
    }

    @Override
    public Ticket getTicketById(Integer id) {
        return ticketRepository.findById(id).orElseThrow(()-> new BadRequestException("Id no válido"));
    }

    @Override
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicket(Ticket ticket) {
        ticketRepository.delete(ticket);
    }

    @Override
    public Ticket buyTicket(String username, Integer idPilgrimage, Integer idFloats) {
        Floats floats = floatsService.getFloatById(idFloats);
        Pilgrimage pilgrimage = pilgrimageService.getPilgrimageById(idPilgrimage);
        User user = userService.getUserByUsername(username);
        Map<Pilgrimage, Integer> availableTickets = floats.getAvailableTickets();
        Integer ticketsAvailable = availableTickets.getOrDefault(pilgrimage, 0);
        if (ticketsAvailable <= 0) {
            throw new IllegalStateException("No tickets available for this Pilgrimage and Floats");
        }
        availableTickets.put(pilgrimage, ticketsAvailable - 1);
        floats.setAvailableTickets(availableTickets);
        floatsService.saveFloat(floats);
        Ticket newTicket =  Ticket.builder()
                .date(LocalDateTime.now())
                .user(user)
                .floats(floats)
                .pilgrimage(pilgrimage)
                .build();
        return saveTicket(newTicket);
    }
}
