package tfg.romerias.pilgrimage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PilgrimageResponse {
    private Integer id;
    private String name;
    private String place;
    private String description;
    private String url;
    private LocalDateTime date;
    private String route;
    private String image;

}
