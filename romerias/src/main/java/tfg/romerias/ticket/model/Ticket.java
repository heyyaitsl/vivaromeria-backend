package tfg.romerias.ticket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tfg.romerias.floats.model.Floats;
import tfg.romerias.user.model.User;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn( name = "username")
    User user;

    @ManyToOne
    @JoinColumn(name = "id_float")
    Floats floats;
}
