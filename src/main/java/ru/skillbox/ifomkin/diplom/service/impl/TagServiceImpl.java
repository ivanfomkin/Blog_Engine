package ru.skillbox.ifomkin.diplom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.ifomkin.diplom.model.Tag;
import ru.skillbox.ifomkin.diplom.repository.TagRepository;
import ru.skillbox.ifomkin.diplom.service.TagService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> getTags(String[] tagsArray) {
        List<Tag> tags = new ArrayList<>();
        for (String tagName : tagsArray) {
            Tag currentTag = tagRepository.findByName(tagName.toUpperCase());
            if (currentTag == null) {
                currentTag = new Tag(tagName);
                tagRepository.save(currentTag);
            }
            tags.add(currentTag);
        }
        return tags;
    }

    @Override
    public List<String> getAllTags() {
        return tagRepository.findAllTags();
    }

    @Override
    public float getTagWeight(String tag) {
        Float abnormalWeightForCurrentTag = tagRepository.calculateAbnormalWeightForTag(tag);
        String mostPopularTag = tagRepository.findMostPopularTag();
        Float abnormalWeightForPopularTag = tagRepository.calculateAbnormalWeightForTag(mostPopularTag);
        Float normalizationFactor = 1 / abnormalWeightForPopularTag;
        Float normalizedWeight = abnormalWeightForCurrentTag * normalizationFactor;
        return normalizedWeight;
    }

    @Override
    public Map<String, Float> getTagMapWithWeight() {
        Map<String, Float> map = new HashMap<>();
        List<String> tags = tagRepository.findAllTags();
        for (String tag : tags) {
            map.put(tag, getTagWeight(tag));
        }
        return map;
    }
}
