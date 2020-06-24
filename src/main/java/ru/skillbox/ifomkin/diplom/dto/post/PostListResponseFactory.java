package ru.skillbox.ifomkin.diplom.dto.post;

import ru.skillbox.ifomkin.diplom.dto.user.UserInPostResponse;
import ru.skillbox.ifomkin.diplom.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostListResponseFactory {
    public static PostListResponse getPosts(List<Post> posts) {
        return new PostListResponse(posts.size(), getPostListResponse(posts));
    }

    private static List<PostResponse> getPostListResponse(List<Post> posts) {
        List<PostResponse> postResponses = new ArrayList<>();
        posts.forEach(p -> postResponses.add(
                new PostResponse(
                        p.getId(),
                        p.getTime(),
                        new UserInPostResponse(
                                p.getUser().getId(),
                                p.getUser().getName()
                        ),
                        p.getTitle(),
                        p.getText().length() > 250 ? p.getText().substring(0, 250) : p.getText(),
                        15,
                        10,
                        p.getComments().size(),
                        p.getViewCount()
                )
        ));

        return postResponses;
    }
}
