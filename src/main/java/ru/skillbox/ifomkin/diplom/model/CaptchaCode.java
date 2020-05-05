package ru.skillbox.ifomkin.diplom.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "captcha_codes")
public class CaptchaCode implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    //в postgres нет типа DATETIME, тип будет TIMESTAMP
    private LocalDateTime time;

    //в postgres нет типа данных TINYTEXT, так что просто ограничим
    //длину varchar в 80 символов, этого достаточно для капчи
    @Column(nullable = false, length = 80)
    private String text;

    @Column(name = "secret_code", nullable = false, length = 80)
    private String code;
}