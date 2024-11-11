package tfg.romerias.comments.service;

import tfg.romerias.comments.model.Comment;

import java.util.List;

public interface ICommentService {
    List<Comment> getCommentsByPilgrimage(Integer idPilgrimage);
    List<Comment> getCommentsByUser(String username);
    Comment saveComment(Comment comment);
}
