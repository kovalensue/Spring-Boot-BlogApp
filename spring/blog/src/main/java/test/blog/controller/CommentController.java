package test.blog.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import test.blog.model.Comment;
import test.blog.model.Post;
import test.blog.service.CommentService;
import test.blog.service.PostService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;

    /**
     * Create method for creating new Comment.
     * @param comment - new Comment
     * @param postId - ID of Post we are commenting
     * @return newly created Comment
     */
    @PostMapping("/{postId}/comments")
    public Comment create(@RequestBody Comment comment, @PathVariable("postId") Long postId) {
        Post post = this.postService.findById(postId);
        return this.commentService.create(comment.getUserName(), comment.getEmail(), comment.getText(), post);
    }

    /**
     * Get method to retrieve details about Comment.
     * @param id - id of a Comment
     * @return found Comment
     */
    @GetMapping("/{postId}/comments/{id}")
    public Comment findById(@PathVariable Long id) {
        return this.commentService.findById(id);
    }

    /**
     * 
     * @param postId
     * @return
     */
    @GetMapping("/{postId}/comments")
    public List<Comment> findAllByPostID(@PathVariable("postId") Long postId) {
        return this.commentService.findAllByPostID(postId);
    }

    @PutMapping("/{postId}/comments/{id}")
    public Comment update(@RequestBody Comment comment, @PathVariable("id") Long id) {
        return this.commentService.update(id, comment.getText());
    }

    @DeleteMapping("/{postId}/comments/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.commentService.delete(id);
    }

    @DeleteMapping("/{postId}/comments")
    public void deleteAll(@PathVariable("postId") Long postId) {
        this.commentService.deleteAllByPostID(postId);
    }

}