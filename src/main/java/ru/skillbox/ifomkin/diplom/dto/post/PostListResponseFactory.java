package ru.skillbox.ifomkin.diplom.dto.post;

import ru.skillbox.ifomkin.diplom.dto.Dto;
import ru.skillbox.ifomkin.diplom.dto.user.UserInPostResponse;
import ru.skillbox.ifomkin.diplom.model.Post;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PostListResponseFactory {
    public static PostListResponse getPosts(List<Post> posts, int offset, int limit, String mode) {
        return new PostListResponse(posts.size(), getPostListResponse(posts, offset, limit, mode));
    }

    private static List<Dto> getPostListResponse(List<Post> posts, int offset, int limit, String mode) {
        if (mode.equals("recent")) {
            posts.sort(Comparator.comparing(Post::getTime).reversed());
        }
        if (mode.equals("popular")) {
            posts.sort((
                    Comparator.comparingInt(post -> post.getComments().size())
            ));
        }
        if (mode.equals("best")) {
            posts.sort((
                    Comparator.comparingInt(post ->
                            post.getVotes().stream()
                                    .filter(vote -> vote.getValue() == 1)
                                    .collect(Collectors.toList()).size())
            ));
        }
        if (mode.equals("early")) {
            posts.sort((
                    Comparator.comparing(Post::getTime)
            ));
        }
        List<Dto> postDtoList = getElementsWithLimit(posts, offset, limit)
                .stream().map(post -> new PostResponse(
                        post.getId(),
                        post.getTime().toEpochSecond(ZoneOffset.UTC),
                        new UserInPostResponse(
                                post.getUser().getId(),
                                post.getUser().getName()
                        ),
                        post.getTitle(),
                        post.getText().length() > 250 ? post.getText().substring(0, 250) : post.getText(),
                        15,
                        10,
                        post.getComments().size(),
                        post.getViewCount()
                )).collect(Collectors.toList());
        return postDtoList;
    }

    public static List<Post> getElementsWithLimit(List<Post> list, int offset, int limit) {
        int lastElementIndex = offset + limit;
        int lastListIndex = list.size();
        if (lastListIndex > offset) { //Если есть элементы, входящие в нужный диапазон
            if (lastElementIndex <= lastListIndex) {
                return list.subList(offset, lastElementIndex);
            } else {
                return list.subList(offset, lastListIndex);
            }
        }
        return new ArrayList<>();
    }
}
