package ru.skillbox.ifomkin.diplom.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    //т.к. в postgres нет TINYINT, Будем использовать SMALLINT
    @Column(name = "is_moderator", columnDefinition = "SMALLINT", nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean isModerator;
    //в postgres нет типа DATETIME, тип будет TIMESTAMP
    @Column(name = "reg_time", nullable = false)
    private LocalDateTime regTime;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    private String code;
    @Column(columnDefinition = "TEXT")
    private String photo;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Post> posts;
}