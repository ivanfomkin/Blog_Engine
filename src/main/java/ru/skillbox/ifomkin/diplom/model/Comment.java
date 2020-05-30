package ru.skillbox.ifomkin.diplom.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "post_comments")
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @ManyToOne(optional = false)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private LocalDateTime time;

    @NotNull
    @Type(type = "text")
    private String text;

    @OneToMany(mappedBy = "parent")
    private List<Comment> children;
}
