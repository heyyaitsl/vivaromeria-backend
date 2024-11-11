package tfg.romerias.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tfg.romerias.comments.model.Comment;
import tfg.romerias.floats.model.Floats;

import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private String username;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;
    private String role;
    private String photo;
    private Set<Integer> floats = new HashSet<>();
    private Set<Comment> comments = new HashSet<>();



}
