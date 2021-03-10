package ru.skillbox.ifomkin.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skillbox.ifomkin.diplom.dto.tag.response.TagWithWeightFromDb;
import ru.skillbox.ifomkin.diplom.model.Tag;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
    Tag findByName(String name);

    @Query(value = "select t.name from Tag t")
    List<String> findAllTags();

    @Query(value = "WITH TagWithWeight AS (SELECT t.name, CAST(COUNT(*) AS REAL) / " +
            "(SELECT count(*) FROM posts p WHERE p.is_active = 1 AND p.moderation_status = 'ACCEPTED' " +
            "AND p.time <= NOW()) AS abnormalWeight FROM tag2post JOIN tags t ON t.id = tag2post.tag_id " +
            "GROUP BY name, t.id), normalizationFactor AS (SELECT CAST(1 AS REAL) / MAX(t.abnormalWeight) " +
            "AS max FROM TagWithWeight t) SELECT t.name, (t.abnormalWeight * normalizationFactor.max) " +
            "AS weight FROM TagWithWeight t, normalizationFactor WHERE t.name LIKE %:query%", nativeQuery = true)
    List<TagWithWeightFromDb> findTagsWithAbnormalWeight(@Param("query") String query);
}
