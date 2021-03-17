package ru.skillbox.ifomkin.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.ifomkin.diplom.dto.statistic.response.StatisticResponseFromDb;
import ru.skillbox.ifomkin.diplom.model.GlobalSetting;

import java.util.List;

@Repository
public interface GlobalSettingRepository extends JpaRepository<GlobalSetting, Integer> {
    List<GlobalSetting> findAll();

    GlobalSetting findGlobalSettingByCode(String code);

    @Query(value = "WITH votes AS (SELECT * FROM post_votes v JOIN posts p2 " +
            "ON p2.id = v.post_id WHERE p2.is_active = 1 " +
            "AND p2.moderation_status = 'ACCEPTED' AND p2.time <= now()), " +
            "valid_posts AS (SELECT * FROM posts p WHERE is_active = 1 AND " +
            "moderation_status = 'ACCEPTED' AND time <= NOW()) " +
            "SELECT count(*) as post, SUM(valid_posts.view_count) as views, " +
            "(SELECT COUNT(*) likes FROM votes pv WHERE pv.value = 1) as likes, " +
            "(SELECT COUNT(*) dislikes FROM votes pv WHERE pv.value = -1) as dislikes, " +
            "(SELECT valPost.time firstp FROM valid_posts valPost ORDER BY " +
            "valPost.time LIMIT 1) as firstp FROM valid_posts", nativeQuery = true)
    StatisticResponseFromDb getGlobalStats();

    @Modifying
    @Transactional
    @Query(value = "UPDATE global_settings SET value = ?2 WHERE code = ?1", nativeQuery = true)
    void updateSettingByCode(String code, String value);

    @Query(value = "SELECT g.value FROM global_settings g WHERE g.code = ?1", nativeQuery = true)
    String getValueByCode(String code);
}
