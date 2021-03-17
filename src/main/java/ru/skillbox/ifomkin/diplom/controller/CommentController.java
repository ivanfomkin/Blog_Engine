package ru.skillbox.ifomkin.diplom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.ifomkin.diplom.dto.comment.factory.CommentResponseFactory;
import ru.skillbox.ifomkin.diplom.dto.comment.request.AddCommentRequest;
import ru.skillbox.ifomkin.diplom.service.CommentService;

import java.security.Principal;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;
    
    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PreAuthorize("hasAuthority('user:write')")
    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody AddCommentRequest request,
                                        Principal principal) {
        return CommentResponseFactory.buildPostResponseEntity(request, principal, commentService);
    }
}
