package ru.skillbox.ifomkin.diplom.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skillbox.ifomkin.diplom.dto.comment.CommentInPost;
import ru.skillbox.ifomkin.diplom.dto.user.UserInPostResponse;
import ru.skillbox.ifomkin.diplom.model.Tag;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullPostResponse extends PostResponse {
    protected List<CommentInPost> comments;
    protected List<Tag> tags;

    @Builder
    public FullPostResponse(int id, LocalDateTime time, UserInPostResponse user, String title, String announce, int likeCount, int dislikeCount, int commentCount, int viewCount, List<CommentInPost> comments, List<Tag> tags) {
        super(id, time, user, title, announce, likeCount, dislikeCount, commentCount, viewCount);
        this.comments = comments;
        this.tags = tags;
    }
}
