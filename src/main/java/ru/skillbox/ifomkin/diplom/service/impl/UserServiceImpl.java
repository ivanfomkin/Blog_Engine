package ru.skillbox.ifomkin.diplom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.ifomkin.diplom.model.User;
import ru.skillbox.ifomkin.diplom.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public List<User> getAll() {
        Iterable<User> taskIterable = repository.findAll();
        List<User> users = new ArrayList<>();
        for (User user : taskIterable) {
            users.add(user);
        }
        return users;
    }
}
