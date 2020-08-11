package test.blog.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import test.blog.model.Comment;
import test.blog.model.Post;
import test.blog.service.CommentService;
import test.blog.service.PostService;

/**
 * Class representing REST api controller for Posts.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    /**
     * Create method for creating a new post.
     * @return instance of newly created Post object
     */
    @PostMapping("/posts")
    public Post create(@RequestBody Post post) {
        return this.postService.create(post.getTitle(), post.getPerex(), post.getBody(), post.getAuthor());
    }

    @GetMapping("/posts")
    public List<Post> findAll() {
        return this.postService.findAll();
    }

    /**
     * Get method for retrieving data about one Post according to given id.
     * @param id - Long id of post
     * @return - string with JSON representation of Post (200) or Not found exception (404)
     */
    @GetMapping("/posts/{id}")
    public Post findById(@PathVariable("id") Long id) {
        Post post = this.postService.findById(id);
        if (post != null) {
            return post;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found.");
        }
    }

    /**
     * 
     * @param post
     * @param id
     * @return
     */
    @PutMapping("/posts/{id}")
    public Post update(@RequestBody Post post, @PathVariable("id") Long id) {
        return this.postService.update(id, post.getTitle(), post.getPerex(), post.getBody(), post.getAuthor());
    }

    @DeleteMapping("/posts/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.postService.delete(id);
    }

    /**
     * Method for adding new comment to the post.
     * @param comment - object of Comment class in from o JSON
     * @param id - id of Post for which we want to add a comment
     * @return returns JSON with Post object including newly created comment
     */
    @PostMapping("/posts/{id}/add-comment")
    public Post addComment(@RequestBody Comment comment, @PathVariable("id") Long id) {
        this.commentService.create(
            comment.getUserName(),
            comment.getEmail(),
            comment.getText(),
            this.postService.findById(id));
        return this.postService.findById(id);
    }

    /**
     * Test get method to test if object is returned as JSON ... WHoaaaa it is!!!
     * @return object of Post class
     */
    @GetMapping("post/post")
    public Post create() {
        long post_id = 1;
        Post post = new Post(post_id, "test", "test", "test", "koval", new Date(), null);
        post.setPubDate(new Date());
        long commentID_1 = 1;
        long commnetID_2 = 2;
        Comment comment1 = new Comment(commentID_1, "funky", "email@domain.com", "test 1", new Date(), post);
        Comment comment2 = new Comment(commnetID_2, "funky", "koval@domain.com", "test 2", new Date(), post);
        List<Comment> list = new ArrayList<>();
        list.add(comment1);
        list.add(comment2);
        post.setPostCommnets(list);
        return post;
    }

    /**
     * Test get method to test if object is returned as JSON ... WHoaaaa it is!!!
     * @return object of Post class
     */
    @GetMapping("post/comment")
    public Comment getComment() {
        long post_id = 1;
        Post post = new Post(post_id, "test", "test", "test", "koval", new Date(), null);
        //Post post = new Post("test", "test", "test", "koval");
        post.setPubDate(new Date());
        long commentID_1 = 1;
        //long commnetID_2 = 2;
        Comment comment1 = new Comment(commentID_1, "funky", "email@domain.com", "test 1", new Date(), post);
        //new Comment(commentID_1, "funky",  "email@domain.com", "test commment 1", post);
        //Comment comment2 = new Comment(commnetID_2, "funky", "email@domain.com", "test commment 2", post);
        //comment1.setCreated(new Date());
        //comment2.setCreated(new Date());
        Set<Comment> set = new HashSet<>();
        set.add(comment1);
        //set.add(comment2);
        //post.setPostCommnets(set);
        return comment1;
    }

}