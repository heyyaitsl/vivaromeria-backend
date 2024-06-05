package tfg.romerias.ticket.converter;

import org.springframework.stereotype.Component;
import tfg.romerias.floats.model.Floats;
import tfg.romerias.floats.service.FloatsService;
import tfg.romerias.ticket.model.Ticket;
import tfg.romerias.ticket.model.TicketRequest;
import tfg.romerias.ticket.model.TicketResponse;
import tfg.romerias.user.model.User;


@Component
public class TicketConverter {

    private final FloatsService floatsService;

    public TicketConverter(FloatsService floatsService) {
        this.floatsService = floatsService;
    }

    public TicketResponse convertToResponse(final Ticket ticket){
        return new TicketResponse(ticket.getId(),ticket.getDate(),
                ticket.getUser().getUsername(),ticket.getFloats().getName());
    }

    public Ticket convertFromRequest(final TicketRequest ticketRequest){
        return new Ticket(ticketRequest.getId(), ticketRequest.getDate(),
                getUser(ticketRequest.getUsername()), getFloat(ticketRequest.getIdFloats()));

    }

    private User getUser(String username){
        return new User();
    }

    private Floats getFloat(Integer id){
        return floatsService.getFloatById(id);
    }

}
