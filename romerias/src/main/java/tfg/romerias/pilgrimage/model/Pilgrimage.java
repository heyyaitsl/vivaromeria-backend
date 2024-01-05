package tfg.romerias.pilgrimage.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pilgrimage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String place;
    private String description;
    private String url;
    @Column(nullable = false)
    private LocalDateTime date;
    private String route;

    @Lob
    @Column(columnDefinition = "tinyblob")
    private byte[] image;

}
