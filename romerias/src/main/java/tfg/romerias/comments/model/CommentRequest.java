package tfg.romerias.comments.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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

}