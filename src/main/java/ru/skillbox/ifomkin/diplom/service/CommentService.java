package ru.skillbox.ifomkin.diplom.service;

import ru.skillbox.ifomkin.diplom.model.Comment;
import ru.skillbox.ifomkin.diplom.model.Post;

import java.util.List;

public interface CommentService {
    List<Comment> findByPost(Post post);
}
