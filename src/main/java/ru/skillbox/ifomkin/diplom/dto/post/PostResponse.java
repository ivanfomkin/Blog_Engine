package ru.skillbox.ifomkin.diplom.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skillbox.ifomkin.diplom.dto.user.UserInPostResponse;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    protected int id;
    protected LocalDateTime time;
    protected UserInPostResponse user;
    protected String title;
    protected String announce;
    protected int likeCount;
    protected int dislikeCount;
    protected int commentCount;
    protected int viewCount;
}