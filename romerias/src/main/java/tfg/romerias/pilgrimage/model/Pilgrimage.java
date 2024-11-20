package tfg.romerias.pilgrimage.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tfg.romerias.comments.model.Comment;
import tfg.romerias.floats.model.Floats;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
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
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;

    @Column(nullable = false)
    private Integer status;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "pilgrimage_floats",
            joinColumns = @JoinColumn(name = "pilgrimage_id"),
            inverseJoinColumns = @JoinColumn(name = "float_id")
    )
    @JsonManagedReference
    private Set<Floats> floats = new HashSet<>();

    @OneToMany(mappedBy = "pilgrimage")
    private Set<Comment> comments = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pilgrimage)) return false;
        Pilgrimage that = (Pilgrimage) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
