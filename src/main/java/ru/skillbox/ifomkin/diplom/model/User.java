package ru.skillbox.ifomkin.diplom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.hibernate.annotations.Type;
import ru.skillbox.ifomkin.diplom.dto.post.PostResponse;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(PostResponse.class)
    private Integer id;

    @NotNull
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isModerator;

    @NotNull
    private LocalDateTime regTime;

    @NotNull
    @JsonView(PostResponse.class)
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;

    private String code;

    @Type(type = "text")
    private String photo;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> posts;

    @OneToMany(mappedBy = "moderator")
    @JsonIgnore
    private List<Post> moderatedPosts;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Vote> votes;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Comment> comments;
}