package tfg.romerias.comments.service;

import org.springframework.stereotype.Service;
import tfg.romerias.comments.model.Comment;
import tfg.romerias.comments.repository.CommentRepository;
import tfg.romerias.exceptions.BadRequestException;

import java.util.List;

@Service
public class CommentService implements ICommentService{
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getCommentsByPilgrimage(Integer idPilgrimage) {
        return commentRepository.findByPilgrimageId(idPilgrimage);
    }

    @Override
    public List<Comment> getCommentsByUser(String username) {
        return commentRepository.findByUserUsername(username);
    }

    @Override
    public Comment getCommentById(Integer id) {
        return commentRepository.findById(id).orElseThrow(() -> new BadRequestException("Id no válido"));
    }

    @Override
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }
}
