package ru.skillbox.ifomkin.diplom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.ifomkin.diplom.dto.post.request.PostRequest;
import ru.skillbox.ifomkin.diplom.model.Post;
import ru.skillbox.ifomkin.diplom.model.TagInPost;
import ru.skillbox.ifomkin.diplom.model.User;
import ru.skillbox.ifomkin.diplom.model.enumerated.Status;
import ru.skillbox.ifomkin.diplom.repository.PostRepository;
import ru.skillbox.ifomkin.diplom.repository.TagInPostRepository;
import ru.skillbox.ifomkin.diplom.repository.UserRepository;
import ru.skillbox.ifomkin.diplom.service.PostService;
import ru.skillbox.ifomkin.diplom.service.TagInPostService;
import ru.skillbox.ifomkin.diplom.service.TagService;

import java.security.Principal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final TagInPostRepository tagInPostRepository;
    private final UserRepository userRepository;
    private final TagInPostService tagInPostService;
    private final TagService tagService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, TagInPostRepository tagInPostRepository, UserRepository userRepository, TagInPostService tagInPostService, TagService tagService) {
        this.postRepository = postRepository;
        this.tagInPostRepository = tagInPostRepository;
        this.userRepository = userRepository;
        this.tagInPostService = tagInPostService;
        this.tagService = tagService;
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post findById(int id,
                         Principal principal) {
        User user = principal == null ? null : userRepository.findByEmail(principal.getName());
        Post post = postRepository.findById(id);
        if (user == null && post != null) {
            incrementViewCount(post);
        } else if (post != null && !user.getIsModerator()) {
            if (!post.getUser().equals(user)) {
                incrementViewCount(post);
            }
        }
        return post;
    }

    @Override
    public List<Post> findValidPosts() {
//        return postRepository.findActivePosts().stream()
//                .filter(post -> post.getTime().before(new Date()))
//                .collect(Collectors.toList());

        return postRepository.findActivePosts();
    }

    @Override
    public List<Post> searchPosts(String query) {
        return postRepository.searchValidPosts(query.toLowerCase());
    }

    @Override
    public List<Post> findByDate(String date) {
        LocalDate localDate = LocalDate.parse(date);
        return findValidPosts().stream()
                .filter(post -> post.getTime().equals(localDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> findByTag(String tag) {
        return tagInPostRepository.findByTag(tag).stream()
                .map(TagInPost::getPost)
                .filter(post -> post.getTime().isBefore(LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> findByStatusForModerator(String status, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        switch (status) {
            case "new":
                return postRepository.findByModerationStatus(Status.NEW);
            case "accepted":
                return postRepository.findByModerationStatusAndAndModerator(
                        Status.ACCEPTED, user);
            case "declined":
                return postRepository.findByModerationStatusAndAndModerator(
                        Status.DECLINED, user);
            default:
                return null;
        }
    }

    @Override
    public List<Post> findByStatusForUser(String status, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        switch (status) {
            case "inactive":
                return postRepository.findByIsActiveAndUser(
                        false, user);
            case "pending":
                return postRepository.findByIsActiveAndUserAndModerationStatus(
                        true, user, Status.NEW);
            case "declined":
                return postRepository.findByIsActiveAndUserAndModerationStatus(
                        true, user, Status.DECLINED);
            case "published":
                return postRepository.findByIsActiveAndUserAndModerationStatus(
                        true, user, Status.ACCEPTED);
            default:
                return null;
        }
    }

    @Override
    public Integer countByModerationStatus(Status status) {
        return postRepository.countByModerationStatus(Status.NEW);
    }

    @Override
    public void incrementViewCount(Post post) {
        post.setViewCount(post.getViewCount() + 1);
        postRepository.save(post);
    }

    @Override
    public boolean createPost(PostRequest postRequest, Principal principal) {
        if (checkValidPostRequest(postRequest)) {
            User user = userRepository.findByEmail(principal.getName());
            Post post = new Post();
            if (postRequest.getTimestamp() < System.currentTimeMillis()) {
                setPostTime(post, System.currentTimeMillis());
            } else {
                setPostTime(post, postRequest.getTimestamp());
            }
            post.setIsActive(postRequest.getActive() == 1 ? true : false);
            post.setTitle(postRequest.getTitle());
            post.setText(postRequest.getText());
            post.setModerationStatus(Status.NEW);
            post.setUser(user);
            post.setViewCount(0);
            postRepository.save(post);
            post.setTags(tagInPostService.addTagsToPost(post, tagService.getTags(postRequest.getTags())));
            postRepository.save(post);
            return true;
        }
        return false;
    }

    @Override
    public void setPostTime(Post post, long time) {
        post.setTime(Instant.ofEpochMilli(
                time)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());
    }

    @Override
    public boolean checkValidPostRequest(PostRequest postRequest) {
        return (postRequest.getText().length() > 50) && (postRequest.getTitle().length() > 3);
    }
}
