package tfg.romerias.comments.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private Integer id;
    private String description;
    private Integer valoration;
    private String username;
    private Integer pilgrimageId;
    private LocalDateTime date;

}
