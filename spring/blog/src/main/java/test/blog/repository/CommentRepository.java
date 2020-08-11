package test.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import test.blog.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPostID(Long postId);
    void deleteAllByPostID(Long postId);

}