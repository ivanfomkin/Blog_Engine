package ru.skillbox.ifomkin.diplom.dto.user;

import lombok.Data;
import ru.skillbox.ifomkin.diplom.dto.security.response.UserSecurityResponse;

@Data
public class UserInCommentResponse extends UserInPostResponse {
    private String photo;
    private UserSecurityResponse user;
}
