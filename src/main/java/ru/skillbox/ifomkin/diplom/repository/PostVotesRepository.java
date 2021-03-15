package ru.skillbox.ifomkin.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skillbox.ifomkin.diplom.model.Vote;

@Repository
public interface PostVotesRepository extends JpaRepository<Vote, Long> {
    @Query(value = "SELECT COUNT(*) FROM post_votes pv JOIN posts p " +
            "ON pv.post_id = p.id WHERE pv.user_id = ?1 AND pv.value = 1 " +
            "AND p.moderation_status = 'ACCEPTED' AND p.is_active = 1 " +
            "AND p.time <= NOW()", nativeQuery = true)
    Integer getUserLikeCount(Integer userId);

    @Query(value = "SELECT COUNT(*) FROM post_votes pv JOIN posts p " +
            "ON pv.post_id = p.id WHERE pv.user_id = ?1 AND pv.value = -1 " +
            "AND p.moderation_status = 'ACCEPTED' AND p.is_active = 1 " +
            "AND p.time <= NOW()", nativeQuery = true)
    Integer getUserDislikeCount(Integer userId);

    @Query(value = "SELECT COUNT(*) FROM post_votes pv JOIN posts p " +
            "ON pv.post_id = p.id WHERE pv.value = 1 " +
            "AND p.moderation_status = 'ACCEPTED' AND p.is_active = 1 " +
            "AND p.time <= NOW()", nativeQuery = true)
    Integer getAllLikeCount();

    @Query(value = "SELECT COUNT(*) FROM post_votes pv JOIN posts p " +
            "ON pv.post_id = p.id WHERE pv.value = -1 " +
            "AND p.moderation_status = 'ACCEPTED' AND p.is_active = 1 " +
            "AND p.time <= NOW()", nativeQuery = true)
    Integer allDislikeCount();
}
