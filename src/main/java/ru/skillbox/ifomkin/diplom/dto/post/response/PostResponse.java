package ru.skillbox.ifomkin.diplom.dto.post.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skillbox.ifomkin.diplom.dto.Dto;
import ru.skillbox.ifomkin.diplom.dto.user.UserInPostResponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse implements Dto {
    protected int id;
    protected long timestamp;
    protected UserInPostResponse user;
    protected String title;
    protected String announce;
    protected int likeCount;
    protected int dislikeCount;
    protected int commentCount;
    protected int viewCount;
}