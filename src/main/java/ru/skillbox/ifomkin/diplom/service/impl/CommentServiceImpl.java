package ru.skillbox.ifomkin.diplom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.ifomkin.diplom.model.Comment;
import ru.skillbox.ifomkin.diplom.model.Post;
import ru.skillbox.ifomkin.diplom.repository.CommentRepository;
import ru.skillbox.ifomkin.diplom.service.CommentService;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;

    @Autowired
    public CommentServiceImpl(CommentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Comment> findByPost(Post post) {
        return repository.findAllByPost(post);
    }


//    public List<CommentInPost> getCommentsInPost(Post post) {
//        List<CommentInPost> commentsInPost = new ArrayList<>();
//        for (Comment comment : getComments(post)) {
//            CommentInPost commentInPost = new CommentInPost();
//            UserInComment user = new UserInComment();
//
//            user.setId(comment.getUser().getId());
//            user.setName(comment.getUser().getName());
//            user.setPhoto(comment.getUser().getPhoto());
//
//            commentInPost.setId(comment.getId());
//            commentInPost.setText(comment.getText());
//            commentInPost.setTime(comment.getTime());
//            commentInPost.setUser(user);
//            commentsInPost.add(commentInPost);
//        }
//        return commentsInPost;
//    }

}
