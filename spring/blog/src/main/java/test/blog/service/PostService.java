package test.blog.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import test.blog.model.Post;
import test.blog.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repository;

    /**
     * Create method for creating new Post and storing it to database.
     * @param title - string representing title of the post
     * @param perex - string representing short description of the post
     * @param body - string representing body of the post
     * @param author - string representing author of the post
     * @return Post object of newly created post
     */
    @Transactional
    public Post create(String title, String perex, String body, String author) {
        Post post = new Post(title, perex, body, author);
        // nastavíme datum publikovaání
        post.setPubDate(new Date());
        repository.saveAndFlush(post);
        return post;
    }

    @Transactional(readOnly = true)
    public Post findById(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Post> findAll() {
        return this.repository.findAll();
    }

    @Transactional
    public Post update(Long id, String title, String perex, String body, String author) {
        Optional<Post> p = repository.findById(id);
        if (p.isPresent()) {
            Post post = p.get();
            post.setTitle(title);
            post.setPerex(perex);
            post.setBody(body);
            post.setAuthor(author);
            post.setPubDate(new Date());
            repository.saveAndFlush(post);
            return post;
         } else {
             throw new RuntimeException("No post found.");
         }
    }

    @Transactional
    public void delete(Long id) {
        this.repository.deleteById(id);
    }

}