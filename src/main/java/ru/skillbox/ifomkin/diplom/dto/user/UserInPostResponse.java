package ru.skillbox.ifomkin.diplom.dto.user;

import lombok.Data;
import ru.skillbox.ifomkin.diplom.dto.ResponseDto;

@Data
public class UserInPost implements ResponseDto {
    private int id;
    private String name;
}
