package ru.skillbox.ifomkin.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skillbox.ifomkin.diplom.model.Tag;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
    Tag findByName(String name);

    @Query(value = "select t.name from Tag t")
    List<String> findAllTags();

    @Query(value = "SELECT COUNT(CASE WHEN t.name like %:tagName% THEN 1 END) / CAST (count(*) AS REAL) " +
            "FROM posts p JOIN tag2post t2p ON p.id = t2p.post_id JOIN tags t ON t2p.tag_id = t.id " +
            "WHERE p.is_active = 1 AND p.moderation_status = 'ACCEPTED' AND p.time <= NOW()", nativeQuery = true)
    Float calculateAbnormalWeightForTag(@Param("tagName") String tagName);

    @Query(value = "SELECT t.name FROM tag2post t2p JOIN tags t ON t.id = t2p.tag_id " +
            "GROUP BY t.name ORDER BY COUNT(*) DESC limit 1", nativeQuery = true)
    String findMostPopularTag();
}
