package ru.skillbox.ifomkin.diplom.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isModerator;

    @NotNull
    private LocalDateTime regTime;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;

    private String code;

    @Type(type = "text")
    private String photo;

    @OneToMany(mappedBy = "user")
    private Set<Post> posts;

    @OneToMany(mappedBy = "moderator")
    private Set<Post> moderatedPosts;

    @OneToMany(mappedBy = "user")
    private Set<Vote> votes;

    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;
}