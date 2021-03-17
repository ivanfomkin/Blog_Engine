package ru.skillbox.ifomkin.diplom.model;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;
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
    @LazyCollection(LazyCollectionOption.EXTRA)
    @Where(clause = "value = 1")
    private List<Vote> likes;

    @OneToMany(mappedBy = "post")
    @LazyCollection(LazyCollectionOption.EXTRA)
    @Where(clause = "value = -1")
    private List<Vote> dislikes;

    @OneToMany(mappedBy = "post")
    private List<TagInPost> tags;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
}
