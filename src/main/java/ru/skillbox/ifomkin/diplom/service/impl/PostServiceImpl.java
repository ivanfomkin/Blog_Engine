package ru.skillbox.ifomkin.diplom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.ifomkin.diplom.dto.ResponseDto;
import ru.skillbox.ifomkin.diplom.dto.post.FullPost;
import ru.skillbox.ifomkin.diplom.dto.post.PostElement;
import ru.skillbox.ifomkin.diplom.dto.user.UserInComment;
import ru.skillbox.ifomkin.diplom.dto.user.UserInPost;
import ru.skillbox.ifomkin.diplom.model.Post;
import ru.skillbox.ifomkin.diplom.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository repository;

    @Autowired
    private CommentService commentService;

    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
        repository.findAll().forEach(p -> posts.add(p));
        return posts;
    }

    public List<ResponseDto> getPostsDto() {
        List<ResponseDto> dtoForList = new ArrayList<>();
        findAll().forEach(post -> dtoForList.add(getPostDto(post)));
        return dtoForList;
    }

    public ResponseDto findById(int id) {
        Post post = repository.findById(id);
        FullPost fullPost = new FullPost();

        String text = post.getText();
        UserInComment user = new UserInComment();

        user.setId(post.getUser().getId());
        user.setName(post.getUser().getName());
        user.setPhoto(post.getUser().getPhoto());

        fullPost.setId(post.getId());
        fullPost.setAnnounce(text.length() > 230 ? text.substring(0, 299) : text);
        fullPost.setCommentCount(post.getComments().size());
        fullPost.setDislikeCount(0);
        fullPost.setLikeCount(15);
        fullPost.setTime(post.getTime());
        fullPost.setTitle(post.getTitle());
        fullPost.setUser(user);
        fullPost.setViewCount(post.getViewCount());
        fullPost.setComments(commentService.getCommentsInPost(post));
        fullPost.setTags(new ArrayList<>());
        return fullPost;
    }

    private ResponseDto getPostDto(Post postFromDb) {
        String text = postFromDb.getText();
        UserInPost user = new UserInPost();
        user.setId(postFromDb.getUser().getId());
        user.setName(postFromDb.getUser().getName());
        PostElement post = new PostElement();
        post.setId(postFromDb.getId());
        post.setAnnounce(text.length() > 230 ? text.substring(0, 299) : text);
        post.setCommentCount(postFromDb.getComments().size());
        post.setDislikeCount(0);
        post.setLikeCount(15);
        post.setTime(postFromDb.getTime());
        post.setTitle(postFromDb.getTitle());
        post.setUser(user);
        post.setViewCount(postFromDb.getViewCount());
        return post;
    }
}
