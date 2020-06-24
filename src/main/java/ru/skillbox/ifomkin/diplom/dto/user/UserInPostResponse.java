package ru.skillbox.ifomkin.diplom.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInPostResponse {
    private int id;
    private String name;
}
