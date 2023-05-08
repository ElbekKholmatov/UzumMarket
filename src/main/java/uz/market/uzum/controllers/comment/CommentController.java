package uz.market.uzum.controllers.comment;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uz.market.uzum.domains.product.Comment;
import uz.market.uzum.dtos.comment.CommentCreateDto;
import uz.market.uzum.dtos.comment.CommentUpdateDTO;
import uz.market.uzum.services.comment.CommentService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment create(@RequestBody CommentCreateDto commentCreateDto) {
        return commentService.create(commentCreateDto);
    }

    @GetMapping("/{productId}")
    public List<Comment> getCommentsByProductId(@PathVariable Integer productId) {
        return commentService.getComments(productId);
    }

    @PutMapping("/{id}")
    public Comment update(@RequestBody CommentUpdateDTO commentUpdateDTO, @PathVariable Integer id) {
        return commentService.update(commentUpdateDTO, id);
    }

    @DeleteMapping("/{id}")
    public Comment delete(@PathVariable Integer id) {
        return commentService.delete(id);
    }

}
