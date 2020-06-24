package ru.skillbox.ifomkin.diplom.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skillbox.ifomkin.diplom.dto.comment.CommentInPost;
import ru.skillbox.ifomkin.diplom.model.Tag;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullPost extends PostResponse {
    private List<CommentInPost> comments;
    private List<Tag> tags;
}
