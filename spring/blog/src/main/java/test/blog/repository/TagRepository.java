package test.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import test.blog.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

    public Optional<Tag> findByName(String name);

}
