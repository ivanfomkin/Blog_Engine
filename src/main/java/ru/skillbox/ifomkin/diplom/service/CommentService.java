package ru.skillbox.ifomkin.diplom.service;

import ru.skillbox.ifomkin.diplom.dto.comment.request.AddCommentRequest;
import ru.skillbox.ifomkin.diplom.exception.comment.CommentLengthException;
import ru.skillbox.ifomkin.diplom.exception.post.PostNotFoundException;
import ru.skillbox.ifomkin.diplom.model.Comment;
import ru.skillbox.ifomkin.diplom.model.Post;

import java.security.Principal;
import java.util.List;

public interface CommentService {
    List<Comment> findByPost(Post post);

    Integer addComment(AddCommentRequest request, Principal principal)
            throws PostNotFoundException, CommentLengthException;
}
