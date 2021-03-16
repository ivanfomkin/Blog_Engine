package ru.skillbox.ifomkin.diplom.service;

import ru.skillbox.ifomkin.diplom.dto.statistic.response.StatisticResponse;

import java.security.Principal;
import java.util.Map;

public interface BlogInfoService {
    Map<String, String> getBlogInfo();

    StatisticResponse getAllStatistic(Principal principal);
}
