package ru.skillbox.ifomkin.diplom.service;

import org.springframework.data.jpa.repository.Query;
import ru.skillbox.ifomkin.diplom.model.Post;
import ru.skillbox.ifomkin.diplom.model.enumerated.Status;

import java.security.Principal;
import java.util.List;

public interface PostService {

    List<Post> findAll();

    Post findById(int id);

    List<Post> findValidPosts();

    List<Post> searchPosts(String query);

    List<Post> findByDate(String date);

    List<Post> findByTag(String tag);

    List<Post> findByStatus(String status, Principal principal);

    Integer countByModerationStatus(Status status);
}
