package tfg.romerias.pilgrimage.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tfg.romerias.floats.model.Floats;

import java.time.LocalDateTime;
import java.util.Set;

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
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] image;

    @Column(nullable = false)
    private Integer status;

    @ManyToMany
    @JoinTable(
            name = "pilgrimage_floats",
            joinColumns = @JoinColumn(name = "pilgrimage_id"),
            inverseJoinColumns = @JoinColumn(name = "float_id")
    )
    private Set<Floats> floats;


}
