package ru.skillbox.ifomkin.diplom.dto.post.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skillbox.ifomkin.diplom.dto.Dto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest implements Dto {
    private long timestamp;
    private int active;
    private String title;
    private String[] tags;
    private String text;
}
