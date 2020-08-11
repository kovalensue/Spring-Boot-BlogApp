package test.blog.component;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import test.blog.model.Comment;
import test.blog.model.Post;
import test.blog.repository.CommentRepository;
import test.blog.repository.PostRepository;
import test.blog.service.CommentService;
import test.blog.service.PostService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@RequiredArgsConstructor
public class DataGenRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataGenRunner.class);

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PostService postService;
    private final CommentService commentService;

    @Override
    public void run(String... args) throws Exception {
        this.postRepository.deleteAll();
        this.commentRepository.deleteAll();

        Post post1 = this.postService.create("Článek 1 - title", "Článek 1 - perex", "Článek 1 - tělo", "funky");
        logger.info("{}", post1);
        Post post2 = this.postService.create("Článek 2 - title", "Článek 2 - perex", "Článek 2 - tělo", "koval");
        logger.info("{}", post2);
        Post post3 = this.postService.create("Článek 3 - title", "Článek 3 - perex", "Článek 3 - tělo", "funky_koval");
        logger.info("{}", post3);

        Comment comment1 = this.commentService.create("leo", "leo@sieone.cz", "komentář 1", post1);
        logger.info("{}", comment1);
        Comment comment2 = this.commentService.create("radka", "radka@sieone.cz", "komentář 2", post1);
        logger.info("{}", comment2);
        Comment comment3 = this.commentService.create("leo", "leo@sieone.cz", "komentář 3", post1);
        logger.info("{}", comment3);

        List<Comment> comments = this.commentService.findAllByPostID(post1.getID());
        if (comments.isEmpty()) {
            logger.error("Nic jsem nenašel!");
        } else {
            for (Comment c : comments) {
                logger.info("Comment ID: " + c.getId());
            }
        }
    }

}