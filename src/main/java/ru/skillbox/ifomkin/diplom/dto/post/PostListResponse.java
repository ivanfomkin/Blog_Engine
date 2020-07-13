package ru.skillbox.ifomkin.diplom.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skillbox.ifomkin.diplom.dto.Dto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostListResponse {
    private int count;
    private List<Dto> posts;
}
