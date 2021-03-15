package ru.skillbox.ifomkin.diplom.dto.comment.factory;

import org.springframework.http.ResponseEntity;
import ru.skillbox.ifomkin.diplom.dto.comment.request.AddCommentRequest;
import ru.skillbox.ifomkin.diplom.dto.comment.response.AddCommentErrorResponse;
import ru.skillbox.ifomkin.diplom.dto.comment.response.AddCommentResponse;
import ru.skillbox.ifomkin.diplom.dto.comment.response.CommentInPostResponse;
import ru.skillbox.ifomkin.diplom.dto.user.UserInCommentResponse;
import ru.skillbox.ifomkin.diplom.exception.comment.CommentLengthException;
import ru.skillbox.ifomkin.diplom.exception.post.PostNotFoundException;
import ru.skillbox.ifomkin.diplom.model.Comment;
import ru.skillbox.ifomkin.diplom.model.Post;
import ru.skillbox.ifomkin.diplom.service.CommentService;

import java.security.Principal;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommentResponseFactory {
    public static ResponseEntity<?> buildPostResponseEntity(AddCommentRequest request, Principal principal,
                                                            CommentService commentService) {
        AddCommentResponse response = new AddCommentResponse();
        try {
            response.setId(commentService.addComment(request, principal));
            return ResponseEntity.ok(response);
        } catch (PostNotFoundException e) {
            return ResponseEntity.badRequest().build();
        } catch (CommentLengthException e) {
            response.setResult(false);
            response.setErrors(new AddCommentErrorResponse(
                    "Текст комментария не задан или слишком короткий"
            ));
            return ResponseEntity.badRequest().body(response);
        }
    }

    public static List<CommentInPostResponse> buildCommentsResponse(Post post) {
        List<CommentInPostResponse> comments = new ArrayList<>();
        for (Comment comment : post.getComments()) {
            comments.add(buildCommentInPostResponse(comment));
        }
        Collections.reverse(comments);
        return comments;
    }

    public static CommentInPostResponse buildCommentInPostResponse(Comment comment) {
        UserInCommentResponse user = new UserInCommentResponse();
        user.setId(comment.getUser().getId());
        user.setName(comment.getUser().getName());
        user.setPhoto(comment.getUser().getPhoto());
        CommentInPostResponse commentInPostResponse = new CommentInPostResponse(
                comment.getId(),
                comment.getTime().toEpochSecond(
                        OffsetDateTime.now(ZoneId.systemDefault()).getOffset()),
                comment.getText(),
                user
        );
        return commentInPostResponse;
    }
}
