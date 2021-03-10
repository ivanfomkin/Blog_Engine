package ru.skillbox.ifomkin.diplom.service;

import ru.skillbox.ifomkin.diplom.dto.tag.response.TagWithWeightResponse;
import ru.skillbox.ifomkin.diplom.model.Tag;

import java.util.List;

public interface TagService {
    List<Tag> getTags(String[] tagsArray);

    List<String> getAllTags();

    List<TagWithWeightResponse> getTagsWithWeight(String query);
}
