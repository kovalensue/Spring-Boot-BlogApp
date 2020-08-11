package test.blog.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    private long id;
    private String userName;
    private String email;
    private String text;
    private Date created;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Post post;

    /**
     * 
     * @param userName
     * @param email
     * @param text
     */
    public Comment(String userName, String email, String text, Post post) {
        this.userName = userName;
        this.email = email;
        this.text = text;
        this.post = post;
    }
}