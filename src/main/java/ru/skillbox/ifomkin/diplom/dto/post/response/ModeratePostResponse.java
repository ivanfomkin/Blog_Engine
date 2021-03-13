package ru.skillbox.ifomkin.diplom.dto.post.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skillbox.ifomkin.diplom.dto.Dto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModeratePostResponse implements Dto {
    private boolean result;
}
