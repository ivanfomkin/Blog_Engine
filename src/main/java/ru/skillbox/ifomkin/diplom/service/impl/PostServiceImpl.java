package ru.skillbox.ifomkin.diplom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.ifomkin.diplom.model.Post;
import ru.skillbox.ifomkin.diplom.model.TagInPost;
import ru.skillbox.ifomkin.diplom.repository.PostRepository;
import ru.skillbox.ifomkin.diplom.repository.TagInPostRepo;
import ru.skillbox.ifomkin.diplom.service.PostService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final TagInPostRepo tagInPostRepo;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, TagInPostRepo tagInPostRepo) {
        this.postRepository = postRepository;
        this.tagInPostRepo = tagInPostRepo;
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post findById(int id) {
        return postRepository.findById(id);
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
        return tagInPostRepo.findByTag(tag).stream()
                .map(TagInPost::getPost)
                .filter(post -> post.getTime().isBefore(LocalDateTime.now()))
                .collect(Collectors.toList());
    }
}
