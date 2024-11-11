package tfg.romerias.comments.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tfg.romerias.pilgrimage.model.Pilgrimage;
import tfg.romerias.user.model.User;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private Integer valoration;
    @ManyToOne
    @JoinColumn(name = "username")
    private User user;
    @ManyToOne
    @JoinColumn(name = "id_pilgrimage")
    private Pilgrimage pilgrimage;

}
