package ru.skillbox.ifomkin.diplom.service;

import ru.skillbox.ifomkin.diplom.dto.calendar.response.PostCountByDateFromDb;
import ru.skillbox.ifomkin.diplom.dto.post.request.ModeratePostRequest;
import ru.skillbox.ifomkin.diplom.dto.post.request.PostRequest;
import ru.skillbox.ifomkin.diplom.model.Post;
import ru.skillbox.ifomkin.diplom.model.enumerated.Status;

import java.security.Principal;
import java.util.List;

public interface PostService {
    List<Post> findPublishedPosts(int offset, int limit, String mode);

    Integer getCountOfPublishedPosts();

    Integer countByModerationStatus(Status status);

    List<Post> searchPosts(int offset, int limit, String query);

    Integer searchedPostsCount(String query);

    List<Post> findPublishedPostsByDate(int offset, int limit, String date);

    Integer getCountOfPublishedPostsByDate(String date);

    Integer getCountOfPublishedPostsByTag(String tag);

    List<Post> findPublishedPostsByTag(int offset, int limit, String tag);

    Post findById(int id, Principal principal);

    void incrementViewCount(Post post);

    List<Post> findByStatusForModerator(String status, Principal principal, int offset, int limit);

    Integer getCountOfPostsForModeration(String status, Principal principal);

    List<Post> findByStatusForUser(String status, Principal principal, int offset, int limit);

    Integer getCountOfPostsByStatusForUser(String status, Principal principal);

    boolean createPost(PostRequest postRequest, Principal principal);

    boolean updatePost(PostRequest postRequest, Integer postId, Principal principal);

    void setPostTime(Post post, long time);

    boolean checkValidPostRequest(PostRequest postRequest);

    List<Integer> getYearsWithActivePost();

    List<PostCountByDateFromDb> getPostCountByDateFromDb(String year);

    boolean moderatePost(ModeratePostRequest request, Principal principal);
}
