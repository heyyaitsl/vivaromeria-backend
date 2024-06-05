package tfg.romerias.ticket.service;

import org.springframework.stereotype.Service;
import tfg.romerias.exceptions.BadRequestException;
import tfg.romerias.floats.model.Floats;
import tfg.romerias.floats.repository.FloatsRepository;
import tfg.romerias.ticket.model.Ticket;
import tfg.romerias.ticket.repository.TicketRepository;

import java.util.List;

@Service
public class TicketService implements ITicketService{

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
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
}
