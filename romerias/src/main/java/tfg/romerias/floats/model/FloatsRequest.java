package tfg.romerias.floats.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tfg.romerias.pilgrimage.model.Pilgrimage;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FloatsRequest {
    private Integer id;
    private String name;
    private String username;
    private double price;
    private int maxPeople;
    private String description;
    private String image;
    private List<Pilgrimage> pilgrimages;
    private Map<Pilgrimage, Integer> availableTickets;
}
