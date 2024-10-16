package tfg.romerias.floats.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tfg.romerias.pilgrimage.model.Pilgrimage;
import tfg.romerias.user.model.User;

import java.util.*;

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

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @Column(nullable = false)
    private double price;
    private int maxPeople;
    private String description;
    @Lob
    @Column(columnDefinition = "tinyblob")
    private byte[] image;



    @ManyToMany(mappedBy = "floats", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonBackReference
    private Set<Pilgrimage> pilgrimages = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "pilgrimage_floats", joinColumns =
    @JoinColumn(name = "float_id")) @MapKeyJoinColumn(name = "pilgrimage_id")
    @Column(name = "available_tickets")
    private Map<Pilgrimage, Integer> availableTickets = new HashMap<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Floats)) return false;
        Floats floats = (Floats) o;
        return Objects.equals(getId(), floats.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }



}
