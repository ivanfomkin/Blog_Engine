package ru.skillbox.ifomkin.diplom.service;

import org.springframework.data.jpa.repository.Query;
import ru.skillbox.ifomkin.diplom.model.Post;

import java.util.List;

public interface PostService {

    List<Post> findAll();

    Post findById(int id);

    List<Post> findValidPosts();

    List<Post> searchPost(String query);

    List<Post> findByDate(String date);

    List<Post> findByTag(String tag);
}
