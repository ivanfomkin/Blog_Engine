package ru.skillbox.ifomkin.diplom.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.ifomkin.diplom.model.Post;
import ru.skillbox.ifomkin.diplom.model.User;
import ru.skillbox.ifomkin.diplom.model.enumerated.Status;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query(value = "SELECT * FROM posts p WHERE is_active = 1 AND moderation_status = 'ACCEPTED' AND " +
            "time <= NOW() ORDER BY time DESC", nativeQuery = true)
    List<Post> findRecentPublishedPosts(Pageable pageable);

    @Query(value = "SELECT DISTINCT p.*, " +
            "(select count(*) count from post_comments c where c.post_id = p.id) " +
            "FROM posts p LEFT JOIN post_comments pc ON p.id = pc.post_id WHERE p.is_active = 1 " +
            "AND p.moderation_status = 'ACCEPTED' AND p.time <= NOW() GROUP BY p.id, pc.id " +
            "ORDER BY (select count(*) count from post_comments c where c.post_id = p.id) DESC", nativeQuery = true)
    List<Post> findPopularPublishedPosts(Pageable pageable);

    @Query(value = "SELECT * FROM posts p LEFT JOIN " +
            "post_votes pv ON p.id = pv.post_id WHERE p.is_active = 1 " +
            "AND p.moderation_status = 'ACCEPTED' AND p.time <= NOW() " +
            "GROUP BY p.id, pv.id ORDER BY -SUM(pv.value) ASC",
            nativeQuery = true)
    List<Post> findBestPublishedPosts(Pageable pageable);

    @Query(value = "SELECT * FROM posts p WHERE moderation_status = 'ACCEPTED' AND is_active = 1 " +
            "AND time <= NOW() ORDER BY time", nativeQuery = true)
    List<Post> findEarlyPublishedPosts(Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM posts p WHERE is_active = 1 AND moderation_status = 'ACCEPTED' AND " +
            "time <= NOW()", nativeQuery = true)
    Integer countAllPublishedPosts();

    @Query(value = "SELECT * FROM posts p WHERE is_active = 1 AND moderation_status = 'ACCEPTED' AND " +
            "time <= NOW() AND (LOWER(p.text) like %:query% OR LOWER(p.title) like %:query%) " +
            "ORDER BY time DESC", nativeQuery = true)
    List<Post> searchPosts(@Param("query") String query, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM posts p WHERE is_active = 1 AND moderation_status = 'ACCEPTED' AND " +
            "time <= NOW() AND (LOWER(p.text) like %:query% OR LOWER(p.title) like %:query%)", nativeQuery = true)
    Integer countAllSearchedPosts(@Param("query") String query);

    Integer countByModerationStatus(Status status);

    @Query(value = "SELECT * FROM posts p WHERE is_active = 1 AND moderation_status = 'ACCEPTED' AND " +
            "time <= NOW() AND time BETWEEN TO_TIMESTAMP(:date, 'YYYY-MM-DD') AND TO_TIMESTAMP(CONCAT(:date, ' 23:59:59'), 'YYYY-MM-DD HH24-MI-SS')" +
            "ORDER BY time DESC", nativeQuery = true)
    List<Post> findPublishedPostsByDate(@Param("date") String date, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM posts p WHERE is_active = 1 AND moderation_status = 'ACCEPTED' AND " +
            "time <= NOW() AND time BETWEEN TO_TIMESTAMP(:date, 'YYYY-MM-DD') AND TO_TIMESTAMP(CONCAT(:date, ' 23:59:59'), 'YYYY-MM-DD HH24-MI-SS')", nativeQuery = true)
    Integer countPublishedPostsByDate(@Param("date") String date);

    Post findPostById(Integer id);

    @Query(value = "SELECT p.* FROM posts p JOIN tag2post t2p ON p.id = t2p.post_id " +
            "JOIN tags t ON t2p.tag_id = t.id WHERE is_active = 1 AND moderation_status = 'ACCEPTED' AND " +
            "time <= NOW() AND t.name = :tag ORDER BY time DESC", nativeQuery = true)
    List<Post> findPostsByTag(@Param("tag") String tag, Pageable pageable);

    @Query(value = "SELECT count(*) FROM posts p JOIN tag2post t2p ON p.id = t2p.post_id " +
            "JOIN tags t ON t2p.tag_id = t.id WHERE is_active = 1 AND moderation_status = 'ACCEPTED' AND " +
            "time <= NOW() AND t.name = :tag", nativeQuery = true)
    Integer countPostsByTag(@Param("tag") String tag);

    List<Post> findPostsByModerationStatusAndIsActive(Status status, Boolean isActive, Pageable pageable);

    Integer countByModerationStatusAndIsActiveIsTrue(Status status);

    List<Post> findPostsByModerationStatusAndModeratorAndIsActiveIsTrue(Status status, User moderator, Pageable pageable);

    Integer countByModerationStatusAndModeratorAndIsActiveIsTrue(Status status, User moderator);

    List<Post> findPostsByUserAndIsActiveFalse(User user, Pageable pageable);

    Integer countByUserAndIsActiveFalse(User user);

    List<Post> findPostsByUserAndModerationStatusAndIsActiveIsTrue(User user, Status status, Pageable pageable);

    Integer countByUserAndModerationStatusAndIsActiveIsTrue(User user, Status status);

    @Transactional
    @Modifying
    @Query(value = "update Post p set p.viewCount = p.viewCount + 1")
    void incrementViewCount(Post post);
}
