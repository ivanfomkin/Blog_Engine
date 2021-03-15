package ru.skillbox.ifomkin.diplom.dto.security.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordRestoreErrorsResponse {
    private String code;
    private String password;
    private String captcha;
}
