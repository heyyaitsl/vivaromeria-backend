package tfg.romerias.comments.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {

    private Integer id;
    private String description;
    private Integer valoration;
    private String username;
    private Integer pilgrimageId;
    private LocalDateTime date;


}