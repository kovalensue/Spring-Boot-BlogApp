package test.blog.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue
    private long ID;
    private String title;
    private String perex;
    private String body;
    private String author;
    private Date pubDate;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    //@OrderBy
    private List<Comment> postCommnets;

    /**
     * Constructor for creation of Post obejct when we still don't know the ID. Also there is no argument for publish
     * date, because this date is generated automaticaly.
     * @param title
     * @param perex
     * @param body
     * @param author
     */
    public Post(String title, String perex, String body, String author) {
        this.title = title;
        this.perex = perex;
        this.body = body;
        this.author = author;
    }

}