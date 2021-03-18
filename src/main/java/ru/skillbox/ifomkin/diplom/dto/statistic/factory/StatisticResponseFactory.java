package ru.skillbox.ifomkin.diplom.dto.statistic.factory;

import ru.skillbox.ifomkin.diplom.dto.statistic.response.StatisticResponse;
import ru.skillbox.ifomkin.diplom.dto.statistic.response.StatisticResponseFromDb;

import java.time.OffsetDateTime;
import java.time.ZoneId;

public class StatisticResponseFactory {
    public static StatisticResponse buildResponse(StatisticResponseFromDb responseFromDb) {
        StatisticResponse response = new StatisticResponse();
        Integer postsCount = responseFromDb.getPost();
        response.setPostsCount(postsCount == null ? 0 : postsCount);
        Integer likesCount = responseFromDb.getLikes();
        response.setLikesCount(likesCount == null ? 0 : likesCount);
        Integer dislikesCount = responseFromDb.getDislikes();
        response.setDislikesCount(dislikesCount == null ? 0 : dislikesCount);
        Integer viewsCount = responseFromDb.getViews();
        response.setViewsCount(viewsCount == null ? 0 : viewsCount);
        Long firstPublication = responseFromDb.getFirstp() == null ?
                null :
                responseFromDb.getFirstp().toEpochSecond(
                        OffsetDateTime.now(ZoneId.systemDefault()).getOffset()
                );
        response.setFirstPublication(firstPublication);
        return response;
    }
}
