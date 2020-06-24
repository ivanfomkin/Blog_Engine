package ru.skillbox.ifomkin.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.skillbox.ifomkin.diplom.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findById(int id);
}
