package tfg.romerias.floats.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tfg.romerias.pilgrimage.model.Pilgrimage;

import java.util.List;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Floats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private double price;
    private int maxPeople;
    private String description;
    @Lob
    @Column(columnDefinition = "tinyblob")
    private byte[] image;

    @ManyToMany(mappedBy = "floats")
    private List<Pilgrimage> pilgrimages;

    @ElementCollection
    @CollectionTable(name = "pilgrimage_floats", joinColumns =
    @JoinColumn(name = "float_id")) @MapKeyJoinColumn(name = "pilgrimage_id")
    @Column(name = "available_tickets")
    private Map<Pilgrimage, Integer> availableTickets;

}
