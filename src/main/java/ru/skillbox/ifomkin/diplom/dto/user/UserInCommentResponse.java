package ru.skillbox.ifomkin.diplom.dto.user;

import lombok.Data;

@Data
public class UserInCommentResponse extends UserInPostResponse {
    private String photo;
}
