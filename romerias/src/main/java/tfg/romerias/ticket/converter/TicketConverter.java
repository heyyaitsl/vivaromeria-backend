package tfg.romerias.ticket.converter;

import org.springframework.stereotype.Component;
import tfg.romerias.floats.model.Floats;
import tfg.romerias.floats.service.FloatsService;
import tfg.romerias.pilgrimage.model.Pilgrimage;
import tfg.romerias.pilgrimage.service.PilgrimageService;
import tfg.romerias.ticket.model.Ticket;
import tfg.romerias.ticket.model.TicketRequest;
import tfg.romerias.ticket.model.TicketResponse;
import tfg.romerias.user.model.User;


@Component
public class TicketConverter {

    private final FloatsService floatsService;
    private final PilgrimageService pilgrimageService;

    public TicketConverter(FloatsService floatsService, PilgrimageService pilgrimageService) {
        this.floatsService = floatsService;
        this.pilgrimageService = pilgrimageService;
    }

    public TicketResponse convertToResponse(final Ticket ticket){
        return new TicketResponse(ticket.getId(),ticket.getDate(),
                ticket.getUser().getUsername(),ticket.getFloats().getName(),ticket.getPilgrimage().getName());
    }

    public Ticket convertFromRequest(final TicketRequest ticketRequest){
        return new Ticket(ticketRequest.getId(), ticketRequest.getDate(),
                getUser(ticketRequest.getUsername()), getFloat(ticketRequest.getFloatsId()),
                getPilgrimage(ticketRequest.getPilgrimageId()));

    }

    private User getUser(String username){
        return new User();
    }

    private Floats getFloat(Integer id){
        return floatsService.getFloatById(id);
    }

    private Pilgrimage getPilgrimage(Integer id){
        return pilgrimageService.getPilgrimageById(id);
    }


}
