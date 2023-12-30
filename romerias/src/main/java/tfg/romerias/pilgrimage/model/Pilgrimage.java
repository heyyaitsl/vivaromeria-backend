package tfg.romerias.pilgrimage.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pilgrimage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String place;
    private String description;
    private String url;
    private LocalDateTime date;
    private String route;

    @Lob
    @Column(columnDefinition = "tinyblob")
    private byte[] image;

}
