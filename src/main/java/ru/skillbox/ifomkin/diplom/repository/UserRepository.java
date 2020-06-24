package ru.skillbox.ifomkin.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.skillbox.ifomkin.diplom.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(int id);
}
