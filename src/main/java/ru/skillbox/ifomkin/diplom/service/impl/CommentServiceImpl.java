package ru.skillbox.ifomkin.diplom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.ifomkin.diplom.dto.comment.CommentInPost;
import ru.skillbox.ifomkin.diplom.dto.user.UserInComment;
import ru.skillbox.ifomkin.diplom.model.Comment;
import ru.skillbox.ifomkin.diplom.model.Post;
import ru.skillbox.ifomkin.diplom.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository repository;

    public List<CommentInPost> getCommentsInPost(Post post) {
        List<CommentInPost> commentsInPost = new ArrayList<>();
        for (Comment comment: getComments(post)) {
            CommentInPost commentInPost = new CommentInPost();
            UserInComment user = new UserInComment();

            user.setId(comment.getUser().getId());
            user.setName(comment.getUser().getName());
            user.setPhoto(comment.getUser().getPhoto());

            commentInPost.setId(comment.getId());
            commentInPost.setText(comment.getText());
            commentInPost.setTime(comment.getTime());
            commentInPost.setUser(user);
            commentsInPost.add(commentInPost);
        }
        return commentsInPost;
    }
    private List<Comment> getComments(Post post) {
        return repository.findAllByPost(post);
    }
}
