package tfg.romerias.ticket.service;

import tfg.romerias.floats.model.Floats;
import tfg.romerias.pilgrimage.model.Pilgrimage;
import tfg.romerias.ticket.model.Ticket;
import tfg.romerias.user.model.User;

import java.util.List;

public interface ITicketService {
    List<Ticket> getTickets();

    List<Ticket> getTicketsByFloat(Integer idFloats);

    Ticket getTicketById(Integer id);

    Ticket saveTicket(Ticket ticket);

    void deleteTicket(Ticket ticket);

    Ticket buyTicket(String username, Integer idPilgrimage, Integer idFloats);
}
