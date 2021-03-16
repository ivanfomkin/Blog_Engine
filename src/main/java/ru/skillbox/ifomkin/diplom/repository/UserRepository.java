package ru.skillbox.ifomkin.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skillbox.ifomkin.diplom.dto.statistic.response.StatisticResponseFromDb;
import ru.skillbox.ifomkin.diplom.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(int id);

    User findByEmail(String email);

    User findUserByCode(String code);

    Boolean existsByEmail(String email);

    @Query(value = "WITH votes AS (SELECT * FROM post_votes v JOIN posts p2 " +
            "ON p2.id = v.post_id WHERE v.user_id = ?1 AND p2.is_active = 1 " +
            "AND p2.moderation_status = 'ACCEPTED' AND p2.time <= now()), " +
            "valid_posts AS (SELECT * FROM posts p WHERE is_active = 1 AND " +
            "moderation_status = 'ACCEPTED' AND time <= NOW() AND user_id = ?1) " +
            "SELECT count(*) as post, SUM(valid_posts.view_count) as views, " +
            "(SELECT COUNT(*) likes FROM votes pv WHERE pv.value = 1) as likes, " +
            "(SELECT COUNT(*) dislikes FROM votes pv WHERE pv.value = -1) as dislikes, " +
            "(SELECT valPost.time firstp FROM valid_posts valPost ORDER BY " +
            "valPost.time LIMIT 1) as firstp FROM valid_posts", nativeQuery = true)
    StatisticResponseFromDb getMyStatistic(Integer userId);
}
