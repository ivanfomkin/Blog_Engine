package ru.skillbox.ifomkin.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skillbox.ifomkin.diplom.model.Post;
import ru.skillbox.ifomkin.diplom.model.User;
import ru.skillbox.ifomkin.diplom.model.enumerated.Status;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findById(int id);

    @Query(value = "SELECT p from Post p where p.isActive = true " +
            "and p.moderationStatus = 'ACCEPTED'")
    List<Post> findActivePosts();

    //Todo: Chek it for future posts
    @Query(value = "SELECT p from Post p where p.isActive = true " +
            "and p.moderationStatus = 'ACCEPTED' and (lower(p.text) like %:query% " +
            "or lower(p.title) like %:query%)")
    List<Post> searchValidPosts(@Param("query") String query);

    List<Post> findByModerationStatus(Status status);

    List<Post> findByModerationStatusAndAndModerator(Status status, User moderator);

    Integer countByModerationStatus(Status status);
}
