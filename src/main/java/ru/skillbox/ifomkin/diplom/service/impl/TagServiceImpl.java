package ru.skillbox.ifomkin.diplom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.ifomkin.diplom.dto.tag.response.TagWithWeightFromDb;
import ru.skillbox.ifomkin.diplom.dto.tag.response.TagWithWeightResponse;
import ru.skillbox.ifomkin.diplom.model.Tag;
import ru.skillbox.ifomkin.diplom.repository.TagRepository;
import ru.skillbox.ifomkin.diplom.service.TagService;

import java.util.ArrayList;
import java.util.List;

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
    public List<TagWithWeightResponse> getTagsWithWeight(String query) {
        List<TagWithWeightFromDb> listFromDb = tagRepository.findTagsWithAbnormalWeight(query);
        List<TagWithWeightResponse> resultList = new ArrayList<>();
        for (TagWithWeightFromDb tag : listFromDb) {
            resultList.add(
                    new TagWithWeightResponse(
                            tag.getName(),
                            tag.getWeight() < 0.25f ? 0.25f : tag.getWeight()
                    )
            );
        }
        return resultList;
    }
}
