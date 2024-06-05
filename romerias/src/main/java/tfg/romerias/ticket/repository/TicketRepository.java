package tfg.romerias.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tfg.romerias.ticket.model.Ticket;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {

    @Query("SELECT t FROM Ticket t WHERE t.floats.id = ?1")
    List<Ticket> findTicketsByFloat(Integer idFloats);
}
