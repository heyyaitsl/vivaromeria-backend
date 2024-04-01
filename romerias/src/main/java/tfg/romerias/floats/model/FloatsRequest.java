package tfg.romerias.floats.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
