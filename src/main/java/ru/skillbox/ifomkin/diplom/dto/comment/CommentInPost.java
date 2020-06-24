package ru.skillbox.ifomkin.diplom.dto.comment;

import lombok.Data;
import ru.skillbox.ifomkin.diplom.dto.user.UserInCommentResponse;

import java.time.LocalDateTime;

@Data
public class CommentInPost {
    private int id;
    private LocalDateTime time;
    private String text;
    private UserInCommentResponse user;
}
