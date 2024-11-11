package tfg.romerias.comments.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tfg.romerias.comments.model.Comment;
import tfg.romerias.comments.service.ICommentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
@CrossOrigin(value = "http://localhost:5173")
public class CommentController {
    private final ICommentService commentService;

    public CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }
    @GetMapping("user/{username}")
    public List<Comment> getCommentsByUsername(@PathVariable String username){
        return commentService.getCommentsByUser(username);
    }
    @GetMapping("pilgrimage/{id}")
    public List<Comment> getCommentsByPilgrimage(@PathVariable Integer id){
        return commentService.getCommentsByPilgrimage(id);
    }

    @PostMapping()
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment){
        return ResponseEntity.of(Optional.of(commentService.saveComment(comment)));

    }
    @PutMapping("{id}")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment){
        return ResponseEntity.of(Optional.of(commentService.saveComment(comment)));

    }

}
