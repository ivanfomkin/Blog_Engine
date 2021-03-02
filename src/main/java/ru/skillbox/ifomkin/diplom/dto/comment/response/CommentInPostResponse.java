package ru.skillbox.ifomkin.diplom.dto.comment.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.skillbox.ifomkin.diplom.dto.user.UserInCommentResponse;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CommentInPostResponse {
    private int id;
    private long time;
    private String text;
    private UserInCommentResponse user;
}
