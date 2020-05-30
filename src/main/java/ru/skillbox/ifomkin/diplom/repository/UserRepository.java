package ru.skillbox.ifomkin.diplom.dao;

import org.springframework.data.repository.CrudRepository;
import ru.skillbox.ifomkin.diplom.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findById
}
