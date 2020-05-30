package ru.skillbox.ifomkin.diplom.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "post_votes")
public class Vote implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "post_id")
    private Post post;

    @NotNull
    @Type(type = "timestamp")
    private LocalDateTime time;

    @NotNull
    @Type(type = "org.hibernate.type.ByteType")
    private Byte value;
}
