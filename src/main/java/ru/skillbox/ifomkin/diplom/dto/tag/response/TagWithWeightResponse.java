package ru.skillbox.ifomkin.diplom.dto.tag.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skillbox.ifomkin.diplom.dto.Dto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagWithWeightResponse implements Dto {
    private String name;
    private float weight;
}
