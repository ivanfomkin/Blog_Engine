package ru.skillbox.ifomkin.diplom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.ifomkin.diplom.dto.post.factory.PostListResponseFactory;
import ru.skillbox.ifomkin.diplom.dto.post.factory.PostResponseFactory;
import ru.skillbox.ifomkin.diplom.dto.post.request.PostRequest;
import ru.skillbox.ifomkin.diplom.dto.vote.VoteRequest;
import ru.skillbox.ifomkin.diplom.model.Post;
import ru.skillbox.ifomkin.diplom.service.PostService;
import ru.skillbox.ifomkin.diplom.service.PostVotesService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/post")
public class ApiPostController {
    private final PostService postService;
    private final PostVotesService votesService;

    @Value("${content.post.minimum-text-length}")
    private Integer minimumPostTextLength;
    @Value("${content.post.minimum-title-length}")
    private Integer minimumPostTitleLength;

    @Autowired
    public ApiPostController(PostService postService, PostVotesService votesService) {
        this.postService = postService;
        this.votesService = votesService;
    }

    @GetMapping
    public ResponseEntity<?> getPostList(
            @RequestParam(required = false, defaultValue = "0") Integer offset,
            @RequestParam(required = false, defaultValue = "20") Integer limit,
            @RequestParam(required = false, defaultValue = "recent") String mode) {
        return ResponseEntity.ok(PostListResponseFactory
                .getPosts(postService.getCountOfPublishedPosts(),
                        postService.findPublishedPosts(offset, limit, mode)));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchPosts(
            @RequestParam(required = false, defaultValue = "0") Integer offset,
            @RequestParam(required = false, defaultValue = "20") Integer limit,
            @RequestParam(required = false, defaultValue = "") String query
    ) {
        List<Post> posts = postService.searchPosts(offset, limit, query);
        return ResponseEntity.ok(PostListResponseFactory
                .getPosts(postService.searchedPostsCount(query),
                        posts));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Integer id,
                                         Principal principal) {
        Post post = postService.findById(id, principal);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(PostResponseFactory
                .getPost(post));
    }

    @GetMapping("/byDate")
    public ResponseEntity<?> getPostsByDate(
            @RequestParam(required = false, defaultValue = "0") Integer offset,
            @RequestParam(required = false, defaultValue = "20") Integer limit,
            @RequestParam(required = false, defaultValue = "") String date
    ) {
        return ResponseEntity.ok(PostListResponseFactory
                .getPosts(postService.getCountOfPublishedPostsByDate(date),
                        postService.findPublishedPostsByDate(offset, limit, date)));
    }

    @GetMapping("/byTag")
    public ResponseEntity<?> getPostsByTag(
            @RequestParam(required = false, defaultValue = "0") Integer offset,
            @RequestParam(required = false, defaultValue = "20") Integer limit,
            @RequestParam(required = false, defaultValue = "") String tag
    ) {
        return ResponseEntity.ok(PostListResponseFactory
                .getPosts(postService.getCountOfPublishedPostsByTag(tag),
                        postService.findPublishedPostsByTag(offset, limit, tag)));
    }

    @PreAuthorize("hasAuthority('user:moderate')")
    @GetMapping("/moderation")
    public ResponseEntity<?> getNonModeratedPosts(
            @RequestParam(required = false, defaultValue = "0") Integer offset,
            @RequestParam(required = false, defaultValue = "10") Integer limit,
            @RequestParam(required = false, defaultValue = "") String status,
            Principal principal
    ) {
        return ResponseEntity.ok(PostListResponseFactory
                .getPosts(postService.getCountOfPostsForModeration(status, principal),
                        postService.findByStatusForModerator(status, principal, offset, limit)));
    }

    @PreAuthorize("hasAuthority('user:write')")
    @GetMapping("/my")
    public ResponseEntity<?> getMyPosts(
            @RequestParam(required = false, defaultValue = "0") Integer offset,
            @RequestParam(required = false, defaultValue = "10") Integer limit,
            @RequestParam(required = false, defaultValue = "") String status,
            Principal principal) {
        return ResponseEntity.ok(PostListResponseFactory
                .getPosts(postService.getCountOfPostsByStatusForUser(status, principal),
                        postService.findByStatusForUser(status, principal, offset, limit)));
    }

    @PreAuthorize("hasAuthority('user:write')")
    @PostMapping
    public ResponseEntity<?> createPost(
            @RequestBody PostRequest postRequest,
            Principal principal) {
        return ResponseEntity.ok(PostResponseFactory.getPostAddResponse(
                postService.createPost(postRequest, principal), postRequest,
                minimumPostTextLength, minimumPostTitleLength));
    }

    @PreAuthorize("hasAuthority('user:write')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(
            @RequestBody PostRequest postRequest,
            Principal principal,
            @PathVariable Integer id) {
        return ResponseEntity.ok(PostResponseFactory.getPostAddResponse(
                postService.updatePost(postRequest, id, principal), postRequest,
                minimumPostTextLength, minimumPostTitleLength));
    }

    @PostMapping("/like")
    public ResponseEntity<?> like(
            @RequestBody VoteRequest voteRequest,
            Principal principal) {
        return ResponseEntity.ok(votesService.like(voteRequest, principal));
    }

    @PostMapping("/dislike")
    public ResponseEntity<?> dislike(
            @RequestBody VoteRequest voteRequest,
            Principal principal) {
        return ResponseEntity.ok(votesService.dislike(voteRequest, principal));
    }
}