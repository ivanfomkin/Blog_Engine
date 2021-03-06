package ru.skillbox.ifomkin.diplom.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skillbox.ifomkin.diplom.dto.Dto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInPostResponse implements Dto {
    private int id;
    private String name;
}
