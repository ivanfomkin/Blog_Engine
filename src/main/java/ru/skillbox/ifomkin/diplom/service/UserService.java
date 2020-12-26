package ru.skillbox.ifomkin.diplom.service;

import ru.skillbox.ifomkin.diplom.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    Boolean isExists(String email);

    User findByEmail(String email);
}
