package ru.skillbox.ifomkin.diplom.dto.tag.factory;

import ru.skillbox.ifomkin.diplom.dto.tag.response.AllTagsWithWeightResponse;
import ru.skillbox.ifomkin.diplom.dto.tag.response.TagWithWeightResponse;

import java.util.List;

public class TagResponseFactory {
    public static AllTagsWithWeightResponse buildTagResponse(List<TagWithWeightResponse> tagsWithWeight) {
        AllTagsWithWeightResponse response = new AllTagsWithWeightResponse();
        response.setTags(tagsWithWeight);
        return response;
    }
}
