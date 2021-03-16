package ru.skillbox.ifomkin.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillbox.ifomkin.diplom.model.Post;
import ru.skillbox.ifomkin.diplom.model.User;
import ru.skillbox.ifomkin.diplom.model.Vote;

@Repository
public interface PostVotesRepository extends JpaRepository<Vote, Long> {
    Vote findVoteByUserAndAndPost(User user, Post post);
}
