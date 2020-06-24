package ru.skillbox.ifomkin.diplom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity getPostList() {
        return ResponseEntity.status(HttpStatus.OK).body(PostListResponseFactory.getPosts(postService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity getPostById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(PostResponseFactory.getPost(postService.findById(id)));
    }
}
