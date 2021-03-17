package ru.skillbox.ifomkin.diplom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.skillbox.ifomkin.diplom.dto.comment.request.AddCommentRequest;
import ru.skillbox.ifomkin.diplom.exception.comment.CommentLengthException;
import ru.skillbox.ifomkin.diplom.exception.comment.CommentNotFoundException;
import ru.skillbox.ifomkin.diplom.exception.post.PostNotFoundException;
import ru.skillbox.ifomkin.diplom.model.Comment;
import ru.skillbox.ifomkin.diplom.model.Post;
import ru.skillbox.ifomkin.diplom.repository.CommentRepository;
import ru.skillbox.ifomkin.diplom.repository.PostRepository;
import ru.skillbox.ifomkin.diplom.repository.UserRepository;
import ru.skillbox.ifomkin.diplom.service.CommentService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Value("${content.comment.minimum-comment-length}")
    private Integer minimumCommentTextLength;

    @Autowired
    public CommentServiceImpl(CommentRepository repository, PostRepository postRepository, UserRepository userRepository) {
        this.repository = repository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Comment> findByPost(Post post) {
        return repository.findAllByPost(post);
    }

    @Override
    public Integer addComment(AddCommentRequest request, Principal principal)
            throws PostNotFoundException, CommentNotFoundException {
        Comment comment = new Comment();
        Post postById =
                request.getPostId() == null ? null :
                        postRepository.findPostById(request.getPostId());
        if (postById == null) {
            throw new PostNotFoundException("Post with id " + request.getPostId() + " not found");
        }
        if (request.getParentId() != null) {
            Comment parentComment = repository.findCommentById(request.getParentId());
            if (parentComment == null) {
                throw new CommentNotFoundException("Comment with id " + request.getParentId() + " not found");
            }
            comment.setParent(parentComment);
        }
        if (request.getText().length() < minimumCommentTextLength)
            throw new CommentLengthException("Comment length less then" + minimumCommentTextLength + " characters");
        comment.setPost(postById);
        comment.setText(request.getText());
        comment.setTime(LocalDateTime.now(ZoneId.systemDefault()));
        comment.setUser(userRepository.findByEmail(principal.getName()));
        return repository.save(comment).getId();
    }

    //    public List<CommentInPost> getCommentsInPost(Post post) {
//        List<CommentInPost> commentsInPost = new ArrayList<>();
//        for (Comment comment : getComments(post)) {
//            CommentInPost commentInPost = new CommentInPost();
//            UserInComment user = new UserInComment();
//
//            user.setId(comment.getUser().getId());
//            user.setName(comment.getUser().getName());
//            user.setPhoto(comment.getUser().getPhoto());
//
//            commentInPost.setId(comment.getId());
//            commentInPost.setText(comment.getText());
//            commentInPost.setTime(comment.getTime());
//            commentInPost.setUser(user);
//            commentsInPost.add(commentInPost);
//        }
//        return commentsInPost;
//    }

}
