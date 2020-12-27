package ru.skillbox.ifomkin.diplom.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@Table(name = "tag2post")
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class TagInPost implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NonNull
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @NotNull
    @NonNull
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;
}
