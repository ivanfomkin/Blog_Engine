package ru.skillbox.ifomkin.diplom.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skillbox.ifomkin.diplom.dto.security.response.UserSecurityResponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInCommentResponse extends UserInPostResponse {
    private String photo;
}
