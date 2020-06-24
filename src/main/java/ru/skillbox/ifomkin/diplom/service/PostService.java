package ru.skillbox.ifomkin.diplom.service;

import ru.skillbox.ifomkin.diplom.model.Post;

import java.util.List;

public interface PostService {
    List<Post> findAll();

    Post findById(int id);
}
