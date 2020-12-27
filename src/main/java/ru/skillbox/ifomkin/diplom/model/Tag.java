package ru.skillbox.ifomkin.diplom.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "tags")
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Tag implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NonNull
    private String name;

    @OneToMany(mappedBy = "tag")
    private List<TagInPost> posts;
}
