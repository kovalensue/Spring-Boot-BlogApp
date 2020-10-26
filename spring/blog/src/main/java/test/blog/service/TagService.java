package test.blog.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import test.blog.model.Tag;
import test.blog.repository.TagRepository;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository repository;

    @Transactional
    public Tag create(String name) {
        Tag tag = new Tag(name);
        this.repository.saveAndFlush(tag);
        return tag;
    }

    @Transactional
    public Tag findById(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    @Transactional
    public Tag findByName(String name) {
        return this.repository.findByName(name).orElse(null);
    }


}
