package tfg.romerias.ticket.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TicketRequest {
    private Integer id;
    private LocalDateTime date;
    private String username;
    private Integer floatsId;
    private Integer pilgrimageId;
}
