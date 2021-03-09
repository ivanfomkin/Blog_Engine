package ru.skillbox.ifomkin.diplom.dto.tag.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skillbox.ifomkin.diplom.dto.Dto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllTagsWithWeightResponse implements Dto {
    private List<TagWithWeightResponse> tags;
}
