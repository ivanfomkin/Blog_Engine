package ru.skillbox.ifomkin.diplom.dto.security.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skillbox.ifomkin.diplom.dto.Dto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse implements Dto {
    private boolean result;
    private UserSecurityResponse user;
}
