package tfg.romerias.comments.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tfg.romerias.comments.converter.CommentConverter;
import tfg.romerias.comments.model.Comment;
import tfg.romerias.comments.model.CommentRequest;
import tfg.romerias.comments.model.CommentResponse;
import tfg.romerias.comments.service.ICommentService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comments")
@CrossOrigin(value = "http://localhost:5173")
public class CommentController {
    private final ICommentService commentService;
    private final CommentConverter converter;

    public CommentController(ICommentService commentService, CommentConverter converter) {
        this.commentService = commentService;
        this.converter = converter;
    }
    @GetMapping("user/{username}")
    public List<CommentResponse> getCommentsByUsername(@PathVariable String username){
        return commentService.getCommentsByUser(username).stream().map(converter::convertToResponse)
                .collect(Collectors.toList());
    }
    @GetMapping("pilgrimage/{id}")
    public List<CommentResponse> getCommentsByPilgrimage(@PathVariable Integer id){
        return commentService.getCommentsByPilgrimage(id).stream().map(converter::convertToResponse)
                .collect(Collectors.toList());
    }

    @PostMapping()
    public ResponseEntity<CommentResponse> saveComment(@RequestBody CommentRequest commentRequest){
        return ResponseEntity.of(Optional.of(converter.convertToResponse(
        commentService.saveComment(converter.convertFromRequest(commentRequest)))));

    }
    @PutMapping("{id}")
    public ResponseEntity<CommentResponse> updateComment(@RequestBody CommentRequest commentRequest){
        return ResponseEntity.of(Optional.of(converter.convertToResponse(
                commentService.saveComment(converter.convertFromRequest(commentRequest)))));

    }

}
