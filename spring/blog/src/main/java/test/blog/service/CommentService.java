package test.blog.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import test.blog.model.Comment;
import test.blog.model.Post;
import test.blog.repository.CommentRepository;

@Service
@AllArgsConstructor
public class CommentService {

    private CommentRepository repository;

    /**
     * Create method for creating a new Comment and storing it to database.
     * @param userName - string representating name of the comment author
     * @param email - string representating comment author email address
     * @param text - string with text of a comment
     * @return long - ID of a newly created comment
     */
    @Transactional
    public Comment create(String userName, String email, String text, Post post) {
        Comment comment = new Comment(userName, email, text, post);
        comment.setCreated(new Date());
        repository.saveAndFlush(comment);
        return comment;
    }

    /**
     * Simple read method.
     * @param id - Long value
     * @return Commnet object according to given comment id
     */
    @Transactional(readOnly = true)
    public Comment findById(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    /**
     * Sim
     * @return
     */
    @Transactional(readOnly = true)
    public List<Comment> findAllByPostID(Long postId) {
        return this.repository.findAllByPostID(postId);
    }

    /**
     * Update method for updating Commet object according to given ID and saving it to database.
     * @param id - Long value representing id of comment for udpate
     * @param text - string with updated text
     */
    @Transactional
    public Comment update(Long id, String text) {
        Optional<Comment> c = repository.findById(id);
        if (c.isPresent()) {
            Comment comment = c.get();
            comment.setText(text);
            repository.saveAndFlush(comment);
            return comment;
        } else {
            throw new RuntimeException("No comment found.");
        }
    }

    /**
     * Delete method for deleteing a comment.
     * @param id - Logn value with Id of comment for delete.
     */
    @Transactional
    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    @Transactional
    public void deleteAllByPostID(Long postId) {
        this.repository.deleteAllByPostID(postId);
    }

}