package ru.skillbox.ifomkin.diplom.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;
}
