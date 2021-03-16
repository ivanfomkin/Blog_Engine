package ru.skillbox.ifomkin.diplom.dto.vote;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skillbox.ifomkin.diplom.dto.Dto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteRequest implements Dto {
    @JsonProperty("post_id")
    private Integer postId;
}
