package tfg.romerias.pilgrimage.converter;

import org.springframework.stereotype.Component;
import tfg.romerias.comments.model.Comment;
import tfg.romerias.comments.service.ICommentService;
import tfg.romerias.floats.model.Floats;
import tfg.romerias.floats.service.FloatsService;
import tfg.romerias.floats.service.IFloatsService;
import tfg.romerias.pilgrimage.model.Pilgrimage;
import tfg.romerias.pilgrimage.model.PilgrimageRequest;
import tfg.romerias.pilgrimage.model.PilgrimageResponse;

import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PilgrimageConverter {

    private final IFloatsService floatsService;
    private final ICommentService commentService;

    public PilgrimageConverter(final IFloatsService floatsService, ICommentService commentService) {
        this.floatsService = floatsService;
        this.commentService = commentService;
    }

    public PilgrimageResponse convertToResponse(final Pilgrimage pilgrimage){
        return new PilgrimageResponse(pilgrimage.getId(),
                pilgrimage.getName(), pilgrimage.getPlace(), pilgrimage.getDescription(),
                pilgrimage.getUrl(), pilgrimage.getDate(), pilgrimage.getRoute(), getImage(pilgrimage), pilgrimage.getStatus(),
                getFloatsId(pilgrimage.getFloats()), getCommentsId(pilgrimage.getComments()));
    }

    public Pilgrimage convertFromRequest(final PilgrimageRequest pilgrimageRequest){
        return new Pilgrimage(pilgrimageRequest.getId(), pilgrimageRequest.getName(),
                pilgrimageRequest.getPlace(), pilgrimageRequest.getDescription(),
                pilgrimageRequest.getUrl(), pilgrimageRequest.getDate(),
                pilgrimageRequest.getRoute(), getImage(pilgrimageRequest), pilgrimageRequest.getStatus(),
                getFloats(pilgrimageRequest.getFloatsId()), getComments(pilgrimageRequest.getCommentsId()));

    }

    private static byte[] getImage(PilgrimageRequest pilgrimageRequest) {
        final String image = pilgrimageRequest.getImage();
        return decode(image);
    }

    private static String getImage(Pilgrimage pilgrimage) {
        final byte[] image = pilgrimage.getImage();
        return encode(image);
    }

    private static byte[] decode(String image) {
        return image != null ? Base64.getDecoder().decode(image) : null;
    }

    private static String encode(byte[] image) {
        return image != null ? Base64.getEncoder().encodeToString(image) : null;
    }

    private static Set<Integer> getFloatsId(Set<Floats> floats){
        if(floats==null)return null;
        return floats.stream().map(Floats::getId).collect(Collectors.toSet());
    }
    private Set<Floats> getFloats(Set<Integer> floatsId){
        if(floatsId==null)return new HashSet<>();
        return floatsId.stream().map(floatsService::getFloatById).collect(Collectors.toSet());
    }

    private static Set<Integer> getCommentsId(Set<Comment> comments){
        if(comments==null)return null;
        return comments.stream().map(Comment::getId).collect(Collectors.toSet());
    }
    private Set<Comment> getComments(Set<Integer> commentsId){
        if(commentsId==null)return new HashSet<>();
        return commentsId.stream().map(commentService::getCommentById).collect(Collectors.toSet());
    }
}
