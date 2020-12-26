package ru.skillbox.ifomkin.diplom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public boolean getValue() {
        if (value.equalsIgnoreCase("YES"))
            return true;
        else
            return false;
    }
}
