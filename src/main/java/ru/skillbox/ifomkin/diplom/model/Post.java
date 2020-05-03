package ru.skillbox.ifomkin.diplom.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "posts")
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "is_active", columnDefinition = "SMALLINT", nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean isActive;
    @Enumerated(EnumType.STRING)
    @Column(name = "moderation_status", nullable = false)
    private Status moderationStatus = Status.NEW;
    @Column(name = "moderator_id")
    private int moderatorId;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "time", nullable = false)
    //в postgres нет типа DATETIME, тип будет TIMESTAMP
    private LocalDateTime time;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "text", nullable = false, columnDefinition = "TEXT")
    private String text;
    @Column(name = "view_count", nullable = false)
    private int viewCount;
}
