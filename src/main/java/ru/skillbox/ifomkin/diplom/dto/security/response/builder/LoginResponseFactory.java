package ru.skillbox.ifomkin.diplom.dto.security.response.builder;

import ru.skillbox.ifomkin.diplom.dto.security.response.LoginResponse;
import ru.skillbox.ifomkin.diplom.model.User;

public class LoginResponseFactory {
    public static LoginResponse getLoginResponse(boolean result, User user) {
        return new LoginResponse(result, SecurityUserResponseFactory.getUserSecurityResponse(user));
    }
}
