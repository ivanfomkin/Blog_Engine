package ru.skillbox.ifomkin.diplom.dto.statistic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skillbox.ifomkin.diplom.dto.Dto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticResponse implements Dto {
    @JsonProperty("postsCount")
    private Integer postCount;
    @JsonProperty("likesCount")
    private Integer likesCount;
    @JsonProperty("dislikesCount")
    private Integer dislikesCount;
    @JsonProperty("viewsCount")
    private Integer viewsCount;
    @JsonProperty("firstPublication")
    private Long firstPublication;
}
