package ru.skillbox.ifomkin.diplom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        for (String tagName: tagsArray) {
            Tag currentTag = tagRepository.findByName(tagName.toUpperCase());
            if (currentTag == null) {
                currentTag = new Tag(tagName);
                tagRepository.save(currentTag);
            }
            tags.add(currentTag);
        }
        return tags;
    }
}
