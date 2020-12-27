package ru.skillbox.ifomkin.diplom.model;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.boot.context.properties.bind.DefaultValue;
import ru.skillbox.ifomkin.diplom.model.enumerated.Status;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "posts")
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isActive;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status moderationStatus;

    @ManyToOne
    @JoinColumn(name = "moderator_id")
    private User moderator;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private LocalDateTime time;

    @NotNull
    private String title;

    @NotNull
    @Type(type = "text")
    private String text;

    @NotNull
    private Integer viewCount;

    @OneToMany(mappedBy = "post")
    private List<Vote> votes;

    @OneToMany(mappedBy = "tag")
    private List<TagInPost> tags;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
}
