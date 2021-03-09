package ru.skillbox.ifomkin.diplom.service;

import ru.skillbox.ifomkin.diplom.model.Tag;

import java.util.List;
import java.util.Map;

public interface TagService {
    List<Tag> getTags(String[] tagsArray);

    List<String> getAllTags();

    float getTagWeight(String tag);

    Map<String,Float> getTagMapWithWeight();
}
