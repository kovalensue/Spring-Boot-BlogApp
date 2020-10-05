package test.blog.controller;

import java.util.List;

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
import test.blog.model.Post;
import test.blog.service.PostService;

/**
 * Class representing REST api controller for Posts.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

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
}