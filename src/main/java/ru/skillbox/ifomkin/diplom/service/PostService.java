package ru.skillbox.ifomkin.diplom.service;

import ru.skillbox.ifomkin.diplom.dto.post.request.PostRequest;
import ru.skillbox.ifomkin.diplom.model.Post;
import ru.skillbox.ifomkin.diplom.model.enumerated.Status;

import java.security.Principal;
import java.util.List;

public interface PostService {

    List<Post> findAll();

    Post findById(int id, Principal principal);

    List<Post> findValidPosts();

    List<Post> searchPosts(String query);

    List<Post> findByDate(String date);

    List<Post> findByTag(String tag);

    List<Post> findByStatusForModerator(String status, Principal principal);

    List<Post> findByStatusForUser(String status, Principal principal);

    void incrementViewCount(Post post);

    Integer countByModerationStatus(Status status);

    boolean createPost(PostRequest postRequest, Principal principal);

    void setPostTime(Post post, long time);

    boolean checkValidPostRequest(PostRequest postRequest);
}
