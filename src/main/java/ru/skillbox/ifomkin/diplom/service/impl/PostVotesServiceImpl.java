package ru.skillbox.ifomkin.diplom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.ifomkin.diplom.dto.vote.VoteRequest;
import ru.skillbox.ifomkin.diplom.dto.vote.VoteResponse;
import ru.skillbox.ifomkin.diplom.model.Post;
import ru.skillbox.ifomkin.diplom.model.User;
import ru.skillbox.ifomkin.diplom.model.Vote;
import ru.skillbox.ifomkin.diplom.repository.PostRepository;
import ru.skillbox.ifomkin.diplom.repository.PostVotesRepository;
import ru.skillbox.ifomkin.diplom.repository.UserRepository;
import ru.skillbox.ifomkin.diplom.service.PostVotesService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class PostVotesServiceImpl implements PostVotesService {
    private final byte LIKE_VOTE_VALUE = 1;
    private final byte DISLIKE_VOTE_VALUE = -1;
    private final PostVotesRepository votesRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public PostVotesServiceImpl(PostVotesRepository votesRepository, UserRepository userRepository, PostRepository postRepository) {
        this.votesRepository = votesRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public VoteResponse like(VoteRequest request, Principal principal) {
        return new VoteResponse(vote(request, principal, LIKE_VOTE_VALUE));

    }

    @Override
    public VoteResponse dislike(VoteRequest request, Principal principal) {
        return new VoteResponse(vote(request, principal, DISLIKE_VOTE_VALUE));
    }

    @Override
    public boolean vote(VoteRequest request, Principal principal, byte value) {
        Post post = postRepository.findPostById(request.getPostId());
        User user = principal == null ? null : userRepository.findByEmail(principal.getName());
        if (post == null || user == null) {
            return false;
        }

        Vote vote = votesRepository.findVoteByUserAndAndPost(user, post);
        if (vote == null) {
            vote = new Vote();
            vote.setPost(post);
            vote.setUser(user);
            vote.setTime(LocalDateTime.now(ZoneId.systemDefault()));
            vote.setValue(value);
            votesRepository.save(vote);
            return true;
        } else {
            if (vote.getValue() != value) {
                vote.setTime(LocalDateTime.now(ZoneId.systemDefault()));
                vote.setValue(value);
                vote.setTime(LocalDateTime.now(ZoneId.systemDefault()));
                votesRepository.save(vote);
                return true;
            }
        }
        return false;
    }
}
