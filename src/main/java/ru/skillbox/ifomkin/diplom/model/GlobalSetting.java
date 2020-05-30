package ru.skillbox.ifomkin.diplom.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "global_settings")
public class GlobalSetting implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String code;

    @NonNull
    private String name;

    @NonNull
    private String value;
}
