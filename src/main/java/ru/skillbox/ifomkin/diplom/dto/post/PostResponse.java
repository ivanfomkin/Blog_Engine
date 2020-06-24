package ru.skillbox.ifomkin.diplom.dto.post;

import lombok.Data;
import ru.skillbox.ifomkin.diplom.dto.ResponseDto;
import ru.skillbox.ifomkin.diplom.dto.user.UserInPost;
import ru.skillbox.ifomkin.diplom.model.User;

import java.time.LocalDateTime;

@Data
public class PostElement implements ResponseDto {
    private int id;
    private LocalDateTime time;
    private UserInPost user;
    private String title;
    private String announce;
    private int likeCount;
    private int dislikeCount;
    private int commentCount;
    private int viewCount;
}