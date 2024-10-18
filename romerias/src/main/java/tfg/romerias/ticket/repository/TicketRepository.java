package tfg.romerias.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tfg.romerias.ticket.model.Ticket;
import tfg.romerias.user.model.User;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
    List<Ticket> findByUserUsername(String username);

    List<Ticket> findByFloatsId(Integer idFloat);


}
