package ru.skillbox.ifomkin.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skillbox.ifomkin.diplom.model.Post;
import ru.skillbox.ifomkin.diplom.model.User;
import ru.skillbox.ifomkin.diplom.model.Vote;

@Repository
public interface PostVotesRepository extends JpaRepository<Vote, Long> {
    Vote findVoteByUserAndAndPost(User user, Post post);

    @Query(value = "SELECT COUNT(*) FROM post_votes pv WHERE " +
            "pv.post_id = ?1 AND pv.value = 1", nativeQuery = true)
    Integer likeCountByPost(Integer postId);

    @Query(value = "SELECT COUNT(*) FROM post_votes pv WHERE " +
            "pv.post_id = ?1 AND pv.value = -1", nativeQuery = true)
    Integer dislikeCountByPost(Integer postId);
}
