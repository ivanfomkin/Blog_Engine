package ru.skillbox.ifomkin.diplom.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "post_votes")
public class Vote implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "time", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime time;

    //т.к. в postgres нет TINYINT, Будем использовать SMALLINT
    @Column(name = "value", columnDefinition = "SMALLINT", nullable = false)
    private Byte value;
}
