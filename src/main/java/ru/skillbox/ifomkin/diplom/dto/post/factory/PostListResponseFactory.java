package ru.skillbox.ifomkin.diplom.dto.post.factory;

import ru.skillbox.ifomkin.diplom.dto.Dto;
import ru.skillbox.ifomkin.diplom.dto.post.response.PostListResponse;
import ru.skillbox.ifomkin.diplom.dto.post.response.PostResponse;
import ru.skillbox.ifomkin.diplom.dto.user.UserInPostResponse;
import ru.skillbox.ifomkin.diplom.model.Post;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

public class PostListResponseFactory {
    public static PostListResponse getPosts(int postCount, List<Post> posts) {
        return new PostListResponse(postCount, getPostListResponse(posts));
    }

    private static List<Dto> getPostListResponse(List<Post> posts) {
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
                post.getLikes().size(),
                post.getDislikes().size(),
                post.getComments().size(),
                post.getViewCount()
        )).collect(Collectors.toList());
    }
}
