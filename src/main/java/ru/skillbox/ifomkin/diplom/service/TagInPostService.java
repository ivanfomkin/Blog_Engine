package ru.skillbox.ifomkin.diplom.service;

import ru.skillbox.ifomkin.diplom.model.Post;
import ru.skillbox.ifomkin.diplom.model.Tag;
import ru.skillbox.ifomkin.diplom.model.TagInPost;

import java.util.List;

public interface TagInPostService {
    List<TagInPost> addTagsToPost(Post post, List<Tag> tags);
}
