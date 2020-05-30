package ru.skillbox.ifomkin.diplom.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "captcha_codes")
public class CaptchaCode implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private LocalDateTime time;

    @NotNull
    @Column(length = 80)
    private String code;

    @NotNull
    @Column(name = "secret_code", length = 80)
    private String secretCode;
}