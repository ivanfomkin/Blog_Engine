package ru.skillbox.ifomkin.diplom.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.skillbox.ifomkin.diplom.dto.calendar.response.PostCountByDateFromDb;
import ru.skillbox.ifomkin.diplom.dto.post.request.ModeratePostRequest;
import ru.skillbox.ifomkin.diplom.dto.post.request.PostRequest;
import ru.skillbox.ifomkin.diplom.model.Post;
import ru.skillbox.ifomkin.diplom.model.User;
import ru.skillbox.ifomkin.diplom.model.enumerated.Status;
import ru.skillbox.ifomkin.diplom.repository.PostRepository;
import ru.skillbox.ifomkin.diplom.repository.PostVotesRepository;
import ru.skillbox.ifomkin.diplom.repository.UserRepository;
import ru.skillbox.ifomkin.diplom.service.GlobalSettingService;
import ru.skillbox.ifomkin.diplom.service.PostService;
import ru.skillbox.ifomkin.diplom.service.TagInPostService;
import ru.skillbox.ifomkin.diplom.service.TagService;

import java.security.Principal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final TagInPostService tagInPostService;
    private final PostVotesRepository votesRepository;
    private final TagService tagService;
    private final GlobalSettingService settingService;
    @Value("${content.post.minimum-text-length}")
    private Integer minimumPostTextLength;
    @Value("${content.post.minimum-title-length}")
    private Integer minimumPostTitleLength;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, TagInPostService tagInPostService, PostVotesRepository votesRepository, TagService tagService, GlobalSettingService settingService) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.tagInPostService = tagInPostService;
        this.votesRepository = votesRepository;
        this.tagService = tagService;
        this.settingService = settingService;
    }

    @Override
    public List<Post> findPublishedPosts(int offset, int limit, String mode) {
        Pageable pageable = PageRequest.of(offset / limit, limit);
        switch (mode) {
            case "recent":
                return postRepository.findRecentPublishedPosts(pageable);
            case "popular":
                return postRepository.findPopularPublishedPosts(pageable);
            case "best":
                return postRepository.findBestPublishedPosts(pageable);
            case "early":
                return postRepository.findEarlyPublishedPosts(pageable);
            default:
                throw new IllegalArgumentException("Illegal mode: " + mode);
        }
    }

    @Override
    public Integer getCountOfPublishedPosts() {
        return postRepository.countAllPublishedPosts();
    }

    @Override
    public Post findById(int id,
                         Principal principal) {
        User user = principal == null ? null : userRepository.findByEmail(principal.getName());
        Post post = postRepository.findPostById(id);
        if (user == null && post != null) {
            incrementViewCount(post);
        } else if (post != null && !user.getIsModerator()) {
            if (!post.getUser().equals(user)) {
                incrementViewCount(post);
            }
        }
        if (!post.getIsActive() || !post.getModerationStatus().equals(Status.ACCEPTED)) {
            if (user == null) {
                return null;
            } else if (user.getIsModerator() || post.getUser().equals(user)) {
                return post;
            } else {
                return null;
            }
        }
        return post;
    }

    @Override
    public Integer getCountOfPublishedPostsByTag(String tag) {
        return postRepository.countPostsByTag(tag.toUpperCase());
    }

    @Override
    public List<Post> findPublishedPostsByTag(int offset, int limit, String tag) {
        return postRepository.findPostsByTag(tag.toUpperCase(), PageRequest.of(offset / limit, limit));
    }

    @Override
    public List<Post> searchPosts(int offset, int limit, String query) {
        PageRequest pageable = PageRequest.of(offset / limit, limit);
        if (StringUtils.isBlank(query)) {
            return postRepository.findRecentPublishedPosts(pageable);
        } else {
            return postRepository.searchPosts(query.toLowerCase(), pageable);
        }
    }

    @Override
    public Integer searchedPostsCount(String query) {
        if (StringUtils.isBlank(query)) {
            return postRepository.countAllPublishedPosts();
        } else {
            return postRepository.countAllSearchedPosts(query);
        }
    }

    @Override
    public List<Post> findPublishedPostsByDate(int offset, int limit, String date) {
        return postRepository.findPublishedPostsByDate(date, PageRequest.of(offset / limit, limit));
    }

    @Override
    public Integer getCountOfPublishedPostsByDate(String date) {
        return postRepository.countPublishedPostsByDate(date);
    }

    @Override
    public List<Post> findByStatusForModerator(String status, Principal principal, int offset, int limit) {
        User user = userRepository.findByEmail(principal.getName());
        Pageable pageable = PageRequest.of(offset / limit, limit);
        switch (status) {
            case "new":
                return postRepository.findPostsByModerationStatusAndIsActive(Status.NEW, true, pageable);
            case "accepted":
                return postRepository.findPostsByModerationStatusAndModeratorAndIsActiveIsTrue(
                        Status.ACCEPTED, user, pageable);
            case "declined":
                return postRepository.findPostsByModerationStatusAndModeratorAndIsActiveIsTrue(
                        Status.DECLINED, user, pageable);
            default:
                return null;
        }
    }

    @Override
    public Integer getCountOfPostsForModeration(String status, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        switch (status) {
            case "new":
                return postRepository.countByModerationStatusAndIsActiveIsTrue(Status.NEW);
            case "accepted":
                return postRepository.countByModerationStatusAndModeratorAndIsActiveIsTrue(
                        Status.ACCEPTED, user);
            case "declined":
                return postRepository.countByModerationStatusAndModeratorAndIsActiveIsTrue(
                        Status.DECLINED, user);
            default:
                return 0;
        }
    }


    @Override
    public List<Post> findByStatusForUser(String status, Principal principal, int offset, int limit) {
        User user = userRepository.findByEmail(principal.getName());
        Pageable pageable = PageRequest.of(offset / limit, limit);
        switch (status) {
            case "inactive":
                return postRepository.findPostsByUserAndIsActiveFalse(user, pageable);
            case "pending":
                return postRepository.findPostsByUserAndModerationStatusAndIsActiveIsTrue(
                        user, Status.NEW, pageable);
            case "declined":
                return postRepository.findPostsByUserAndModerationStatusAndIsActiveIsTrue(
                        user, Status.DECLINED, pageable);
            case "published":
                return postRepository.findPostsByUserAndModerationStatusAndIsActiveIsTrue(
                        user, Status.ACCEPTED, pageable);
            default:
                return null;
        }
    }

    @Override
    public Integer getCountOfPostsByStatusForUser(String status, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        switch (status) {
            case "inactive":
                return postRepository.countByUserAndIsActiveFalse(user);
            case "pending":
                return postRepository.countByUserAndModerationStatusAndIsActiveIsTrue(
                        user, Status.NEW);
            case "declined":
                return postRepository.countByUserAndModerationStatusAndIsActiveIsTrue(
                        user, Status.DECLINED);
            case "published":
                return postRepository.countByUserAndModerationStatusAndIsActiveIsTrue(
                        user, Status.ACCEPTED);
            default:
                return 0;
        }
    }

    @Override
    public Integer countByModerationStatus(Status status) {
        return postRepository.countByModerationStatus(Status.NEW);
    }

    @Override
    public void incrementViewCount(Post post) {
        postRepository.incrementViewCount(post.getId());
    }

    @Override
    public boolean createPost(PostRequest postRequest, Principal principal) {
        if (checkValidPostRequest(postRequest)) {
            User user = userRepository.findByEmail(principal.getName());
            Post post = new Post();
            if (postRequest.getTimestamp() < System.currentTimeMillis() / 1000) {
                setPostTime(post, System.currentTimeMillis() / 1000);
            } else {
                setPostTime(post, postRequest.getTimestamp());
            }
            post.setIsActive(postRequest.getActive() == 1 ? true : false);
            post.setTitle(postRequest.getTitle());
            post.setText(postRequest.getText());
            if (user.getIsModerator() || !settingService.getPremoderation()) {
                post.setModerationStatus(Status.ACCEPTED);
            } else {
                post.setModerationStatus(Status.NEW);
            }
            post.setUser(user);
            post.setViewCount(0);
            postRepository.save(post);
            post.setTags(tagInPostService.addTagsToPost(post,
                    tagService.getTags(Arrays.stream(postRequest.getTags())
                            .map(String::toUpperCase).toArray(String[]::new))));
            postRepository.save(post);
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePost(PostRequest postRequest, Integer postId, Principal principal) {
        if (checkValidPostRequest(postRequest)) {
            Post postRepositoryById = postRepository.findPostById(postId);
            if (postRepository == null)
                return false;
            if (postRequest.getTimestamp() < System.currentTimeMillis() / 1000) {
                setPostTime(postRepositoryById, System.currentTimeMillis() / 1000);
            } else {
                setPostTime(postRepositoryById, postRequest.getTimestamp());
            }
            postRepositoryById.setIsActive(postRequest.getActive() == 1);
            postRepositoryById.setTitle(postRequest.getTitle());
            postRepositoryById.setText(postRequest.getText());
            postRepository.save(postRepositoryById);
            postRepositoryById.setTags(tagInPostService.addTagsToPost(
                    postRepositoryById, tagService.getTags(Arrays.stream(postRequest.getTags()).map(String::toUpperCase).toArray(String[]::new))
            ));
            Boolean userIsModerator = userRepository.findByEmail(principal.getName()).getIsModerator();
            if (!userIsModerator) {
                postRepositoryById.setModerationStatus(Status.NEW);
            }
            postRepository.save(postRepositoryById);
            return true;
        } else
            return false;
    }

    @Override
    public void setPostTime(Post post, long time) {
        post.setTime(Instant.ofEpochSecond(
                time)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());
    }

    @Override
    public boolean checkValidPostRequest(PostRequest postRequest) {
        return (postRequest.getText().length() > minimumPostTextLength) && (postRequest.getTitle().length() > minimumPostTitleLength);
    }

    @Override
    public List<Integer> getYearsWithActivePost() {
        return postRepository.getYearsWIthActivePosts();
    }

    @Override
    public List<PostCountByDateFromDb> getPostCountByDateFromDb(String year) {
        Integer yearForQuery = year.equals("") ? LocalDate.now().getYear() : Integer.parseInt(year);
        return postRepository.getPostsCountByDate(yearForQuery);
    }

    @Override
    public boolean moderatePost(ModeratePostRequest request, Principal principal) {
        Post post = postRepository.findPostById(request.getPostId());
        User moderator = userRepository.findByEmail(principal.getName());
        if (post == null || moderator == null) {
            return false;
        } else {
            if (request.getDecision().equalsIgnoreCase("accept")) {
                post.setModerationStatus(Status.ACCEPTED);
            } else {
                post.setModerationStatus(Status.DECLINED);
            }
            post.setModerator(moderator);
            postRepository.save(post);
            return true;
        }
    }
}
