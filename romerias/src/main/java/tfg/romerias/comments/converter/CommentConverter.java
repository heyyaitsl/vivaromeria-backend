package tfg.romerias.comments.converter;

import org.springframework.stereotype.Component;
import tfg.romerias.comments.model.Comment;
import tfg.romerias.comments.model.CommentRequest;
import tfg.romerias.comments.model.CommentResponse;
import tfg.romerias.pilgrimage.service.IPilgrimageService;
import tfg.romerias.user.service.IUserService;


@Component
public class CommentConverter {
    private final IUserService userService;
    private final IPilgrimageService pilgrimageService;

    public CommentConverter(IUserService userService, IPilgrimageService pilgrimageService) {
        this.userService = userService;
        this.pilgrimageService = pilgrimageService;
    }

    public CommentResponse convertToResponse(final Comment comment){
        return new CommentResponse(comment.getId(),
                comment.getDescription(), comment.getValoration(), comment.getUser().getUsername(),
                comment.getPilgrimage().getId(), comment.getDate());
    }

    public Comment convertFromRequest(final CommentRequest commentRequest){
        return new Comment(commentRequest.getId(),
                commentRequest.getDescription(), commentRequest.getValoration(), userService.getUserByUsername(commentRequest.getUsername()),
                pilgrimageService.getPilgrimageById(commentRequest.getPilgrimageId()), commentRequest.getDate());
    }


}
