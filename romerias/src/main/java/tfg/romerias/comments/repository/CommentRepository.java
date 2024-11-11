package tfg.romerias.comments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tfg.romerias.comments.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> findByUserUsername(String username);

    List<Comment> findByPilgrimageId(Integer idFloat);
}
