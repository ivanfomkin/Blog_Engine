package ru.skillbox.ifomkin.diplom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.ifomkin.diplom.dto.post.factory.PostListResponseFactory;
import ru.skillbox.ifomkin.diplom.dto.post.factory.PostResponseFactory;
import ru.skillbox.ifomkin.diplom.dto.post.request.PostRequest;
import ru.skillbox.ifomkin.diplom.model.Post;
import ru.skillbox.ifomkin.diplom.service.PostService;

import java.security.Principal;

@RestController
@RequestMapping("api/post")
public class ApiPostController {
    private final PostService postService;

    @Autowired
    public ApiPostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<?> getPostList(
            @RequestParam(required = false, defaultValue = "0") Integer offset,
            @RequestParam(required = false, defaultValue = "20") Integer limit,
            @RequestParam(required = false, defaultValue = "recent") String mode) {
        return ResponseEntity.ok(PostListResponseFactory
                .getPosts(postService.findValidPosts(), offset, limit, mode));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchPosts(
            @RequestParam(required = false, defaultValue = "0") Integer offset,
            @RequestParam(required = false, defaultValue = "20") Integer limit,
            @RequestParam(required = false, defaultValue = "") String query
    ) {
        return ResponseEntity.ok(PostListResponseFactory
                .getPosts(postService.searchPosts(query), offset, limit, query));
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
                .getElementsWithLimit(postService.findByDate(date), offset, limit));
    }

    @GetMapping("/byTag")
    public ResponseEntity<?> getPostsByTag(
            @RequestParam(required = false, defaultValue = "0") Integer offset,
            @RequestParam(required = false, defaultValue = "20") Integer limit,
            @RequestParam(required = false, defaultValue = "") String tag
    ) {
        return ResponseEntity.ok(PostListResponseFactory
                .getElementsWithLimit(postService.findByTag(tag), limit, offset));
    }

    @PreAuthorize("hasAuthority('user:moderate')")
    @GetMapping("/moderation")
    public ResponseEntity<?> getNonModeratedPosts(
            @RequestParam(required = false, defaultValue = "0") Integer offset,
            @RequestParam(required = false, defaultValue = "10") Integer limit,
            @RequestParam(required = false, defaultValue = "") String status,
            Principal principal
    ) {
        String mode = "early";
        if (status.equals("accepted"))
            mode = "recent";
        return ResponseEntity.ok(PostListResponseFactory
                .getPosts(postService.findByStatusForModerator(status, principal), offset, limit, mode));
    }

    @PreAuthorize("hasAuthority('user:write')")
    @GetMapping("/my")
    public ResponseEntity<?> getMyPosts(
            @RequestParam(required = false, defaultValue = "0") Integer offset,
            @RequestParam(required = false, defaultValue = "10") Integer limit,
            @RequestParam(required = false, defaultValue = "") String status,
            Principal principal) {
        String mode = "recent";
        return ResponseEntity.ok(PostListResponseFactory
                .getPosts(postService.findByStatusForUser(status, principal), offset, limit, mode));
    }

    @PreAuthorize("hasAuthority('user:write')")
    @PostMapping
    public ResponseEntity<?> createPost(
            @RequestBody PostRequest postRequest,
            Principal principal) {
        return ResponseEntity.ok(PostResponseFactory.getPostAddResponse(
                postService.createPost(postRequest, principal), postRequest));
    }

    @PreAuthorize("hasAuthority('user:write')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(
            @RequestBody PostRequest postRequest,
            Principal principal,
            @PathVariable Integer id) {
        return ResponseEntity.ok(PostResponseFactory.getPostAddResponse(
                postService.updatePost(postRequest, id, principal), postRequest));
    }
}