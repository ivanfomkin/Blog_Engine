package ru.skillbox.ifomkin.diplom.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    //т.к. в postgres нет TINYINT, Будем использовать SMALLINT
    @Column(name = "is_moderator", columnDefinition = "SMALLINT", nullable = false)
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
}
