package ru.skillbox.ifomkin.diplom.service;

import ru.skillbox.ifomkin.diplom.dto.vote.VoteRequest;
import ru.skillbox.ifomkin.diplom.dto.vote.VoteResponse;

import java.security.Principal;

public interface PostVotesService {
    VoteResponse like(VoteRequest request, Principal principal);

    VoteResponse dislike(VoteRequest request, Principal principal);

    boolean vote(VoteRequest request, Principal principal, byte value);
}
