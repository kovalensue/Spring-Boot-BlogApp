package test.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import test.blog.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}