package tfg.romerias.pilgrimage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tfg.romerias.exceptions.BadRequestException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PilgrimageRequest {
    private Integer id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @NotNull(message="El nombre no puede ser nulo")
    private String name;

    @NotBlank(message = "El lugar no puede estar vacío")
    @NotNull(message="El lugar no puede ser nulo")
    private String place;
    private String description;
    private String url;

    @NotBlank(message = "La fecha no puede estar vacío")
    @NotNull(message="La fecha no puede ser nulo")
    private LocalDateTime date;
    private String route;
    private String image;



}
