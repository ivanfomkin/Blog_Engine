package ru.skillbox.ifomkin.diplom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.ifomkin.diplom.model.Post;
import ru.skillbox.ifomkin.diplom.model.Tag;
import ru.skillbox.ifomkin.diplom.model.TagInPost;
import ru.skillbox.ifomkin.diplom.repository.TagInPostRepository;
import ru.skillbox.ifomkin.diplom.service.TagInPostService;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagInPostServiceImpl implements TagInPostService {
    private final TagInPostRepository tagInPostRepository;

    @Autowired
    public TagInPostServiceImpl(TagInPostRepository tagInPostRepository) {
        this.tagInPostRepository = tagInPostRepository;
    }

    @Override
    public List<TagInPost> addTagsToPost(Post post, List<Tag> tags) {
        List<TagInPost> tagInPostList = new ArrayList<>();
        for (Tag currentTag : tags) {
            TagInPost tagInPost = new TagInPost(post, currentTag);
            tagInPostRepository.save(tagInPost);
            tagInPostList.add(tagInPost);
        }
        return tagInPostList;
    }
}
