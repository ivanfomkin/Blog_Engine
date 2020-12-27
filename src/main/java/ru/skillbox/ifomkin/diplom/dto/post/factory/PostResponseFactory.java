package ru.skillbox.ifomkin.diplom.dto.post.factory;

import ru.skillbox.ifomkin.diplom.dto.post.request.PostRequest;
import ru.skillbox.ifomkin.diplom.dto.post.response.FullPostResponse;
import ru.skillbox.ifomkin.diplom.dto.post.response.PostAddErrorResponse;
import ru.skillbox.ifomkin.diplom.dto.post.response.PostAddResponse;
import ru.skillbox.ifomkin.diplom.dto.post.response.PostResponse;
import ru.skillbox.ifomkin.diplom.dto.user.UserInPostResponse;
import ru.skillbox.ifomkin.diplom.model.Post;
import ru.skillbox.ifomkin.diplom.model.TagInPost;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class PostResponseFactory {
    public static PostResponse getPost(Post post) {
        return new FullPostResponse(
                post.getId(),
                post.getTime().toEpochSecond(ZoneOffset.UTC),
                post.getIsActive(),
                new UserInPostResponse(
                        post.getUser().getId(),
                        post.getUser().getName()
                ),
                post.getTitle(),
                post.getText().length() > 250 ? post.getText().substring(0, 250) : post.getText(),
                post.getText(),
                15,
                10,
                post.getComments().size(),
                post.getViewCount(),
                new ArrayList<>(),
                getTagsFromTagInPost(post.getTags()));
    }

    public static PostAddResponse getPostAddResponse(boolean result, PostRequest postRequest) {
        PostAddErrorResponse postAddErrorResponse = null;
        if (!result) {
            postAddErrorResponse = new PostAddErrorResponse();
            if (postRequest.getText().length() < 50) {
                postAddErrorResponse.setText("Текст публикации слишком короткий");
            }
            if (postRequest.getTitle().length() < 3) {
                postAddErrorResponse.setTitle("Заголовок не установлен или слишком короткий");
            }
        }
        return new PostAddResponse(result, postAddErrorResponse);
    }

    private static List<String> getTagsFromTagInPost(List<TagInPost> list) {
        List<String> tags = new ArrayList<>();
        for (TagInPost tagInPost : list) {
            tags.add(tagInPost.getTag().getName());
        }
        return tags;
    }
}
