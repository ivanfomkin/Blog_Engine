package ru.skillbox.ifomkin.diplom.dto.post.factory;

import ru.skillbox.ifomkin.diplom.dto.Dto;
import ru.skillbox.ifomkin.diplom.dto.post.response.PostListResponse;
import ru.skillbox.ifomkin.diplom.dto.post.response.PostResponse;
import ru.skillbox.ifomkin.diplom.dto.user.UserInPostResponse;
import ru.skillbox.ifomkin.diplom.model.Post;
import ru.skillbox.ifomkin.diplom.service.PostService;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

public class PostListResponseFactory {
    public static PostListResponse getPosts(int postCount, List<Post> posts, PostService postService) {
        return new PostListResponse(postCount, getPostListResponse(posts, postService));
    }

    private static List<Dto> getPostListResponse(List<Post> posts, PostService postService) {
        return posts.stream().map(post -> new PostResponse(
                post.getId(),
                post.getTime().toEpochSecond(
                        OffsetDateTime.now(ZoneId.systemDefault()).getOffset()),
                new UserInPostResponse(
                        post.getUser().getId(),
                        post.getUser().getName()
                ),
                post.getTitle(),
                post.getText().length() > 250 ? post.getText().substring(0, 250) : post.getText(),
                postService.getLikeCountByPost(post),
                postService.getDislikeCountByPost(post),
                post.getComments().size(),
                post.getViewCount()
        )).collect(Collectors.toList());
    }
}
