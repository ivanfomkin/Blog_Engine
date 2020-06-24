package ru.skillbox.ifomkin.diplom.dto.post;

import ru.skillbox.ifomkin.diplom.dto.user.UserInPostResponse;
import ru.skillbox.ifomkin.diplom.model.Post;

import java.util.ArrayList;

public class PostResponseFactory {
    public static PostResponse getPost(Post post) {
        return new FullPostResponse(

                post.getId(),
                post.getTime(),
                new UserInPostResponse(
                        post.getUser().getId(),
                        post.getUser().getName()
                ),
                post.getTitle(),
                post.getText(),
                15,
                10,
                post.getComments().size(),
                post.getViewCount(),
                new ArrayList<>(),
                new ArrayList<>()
        );
    }
}
