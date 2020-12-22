package ru.skillbox.ifomkin.diplom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.ifomkin.diplom.dto.post.PostListResponseFactory;
import ru.skillbox.ifomkin.diplom.dto.post.PostResponseFactory;
import ru.skillbox.ifomkin.diplom.service.PostService;

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
        return ResponseEntity.status(HttpStatus.OK)
                .body(PostListResponseFactory
                        .getPosts(postService.findValidPosts(), offset, limit, mode));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchPosts(
            @RequestParam(required = false, defaultValue = "0") Integer offset,
            @RequestParam(required = false, defaultValue = "20") Integer limit,
            @RequestParam(required = false, defaultValue = "") String query
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(PostListResponseFactory
                        .getPosts(postService.searchPosts(query), offset, limit, query));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(PostResponseFactory
                        .getPost(postService.findById(id)));
    }

    @GetMapping("/byDate")
    public ResponseEntity<?> getPostsByDate(
            @RequestParam(required = false, defaultValue = "0") Integer offset,
            @RequestParam(required = false, defaultValue = "20") Integer limit,
            @RequestParam(required = false, defaultValue = "") String date
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(PostListResponseFactory
                        .getElementsWithLimit(postService.findByDate(date), offset, limit));
    }

    @GetMapping("/byTag")
    public ResponseEntity<?> getPostsByTag(
            @RequestParam(required = false, defaultValue = "0") Integer offset,
            @RequestParam(required = false, defaultValue = "20") Integer limit,
            @RequestParam(required = false, defaultValue = "") String tag
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(PostListResponseFactory
                        .getElementsWithLimit(postService.findByTag(tag), limit, offset));
    }
}