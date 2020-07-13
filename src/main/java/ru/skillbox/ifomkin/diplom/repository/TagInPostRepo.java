package ru.skillbox.ifomkin.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skillbox.ifomkin.diplom.model.Tag;
import ru.skillbox.ifomkin.diplom.model.TagInPost;

import java.util.List;

@Repository
public interface TagInPostRepo extends JpaRepository<TagInPost, Integer> {

    @Query("SELECT t from TagInPost t where t.tag.name = ?1 and t.post.moderationStatus = 'ACCEPTED' and t.post.isActive = true")
    List<TagInPost> findByTag(String tag);
}
