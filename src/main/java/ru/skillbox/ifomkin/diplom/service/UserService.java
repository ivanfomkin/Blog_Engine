package ru.skillbox.ifomkin.diplom.service;

import ru.skillbox.ifomkin.diplom.dto.security.request.RegisterRequest;
import ru.skillbox.ifomkin.diplom.dto.security.response.RegisterResponse;
import ru.skillbox.ifomkin.diplom.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    Boolean isExists(String email);

    User findByEmail(String email);

    RegisterResponse registerUser(RegisterRequest request);

    Boolean checkValidUserName(String username);

    Boolean checkValidPassword(String password);
}
