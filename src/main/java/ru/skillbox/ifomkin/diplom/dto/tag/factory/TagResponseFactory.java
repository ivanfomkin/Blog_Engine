package ru.skillbox.ifomkin.diplom.dto.tag.factory;

import ru.skillbox.ifomkin.diplom.dto.tag.response.AllTagsWithWeightResponse;
import ru.skillbox.ifomkin.diplom.dto.tag.response.TagWithWeightResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TagResponseFactory {
    public static AllTagsWithWeightResponse buildTagResponse(Map<String, Float> tagsWithWeight) {
        AllTagsWithWeightResponse response = new AllTagsWithWeightResponse();
        response.setTags(getTagWithWeightList(tagsWithWeight));
        return response;
    }

    public static List<TagWithWeightResponse> getTagWithWeightList(Map<String, Float> tagsWithWeight) {
        List<TagWithWeightResponse> list = new ArrayList<>();
        for (Map.Entry<String, Float> entry : tagsWithWeight.entrySet()) {
            list.add(new TagWithWeightResponse(entry.getKey(), entry.getValue()));
        }
        return list;
    }
}
