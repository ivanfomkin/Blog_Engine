package ru.skillbox.ifomkin.diplom.dto.statistic.response;

import java.time.LocalDateTime;

public interface StatisticResponseFromDb {
    Integer getPost();

    Integer getLikes();

    Integer getDislikes();

    Integer getViews();

    LocalDateTime getFirstp();
}
