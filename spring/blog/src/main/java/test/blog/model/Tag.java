package test.blog.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Tag {

    @Id
    @GeneratedValue
    private long id;
    private String name;

    public Tag(String name) {
        this.name = name;
    }

}
