package ru.skillbox.ifomkin.diplom.dto.post.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skillbox.ifomkin.diplom.dto.comment.CommentInPost;
import ru.skillbox.ifomkin.diplom.dto.user.UserInPostResponse;
import ru.skillbox.ifomkin.diplom.model.Tag;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullPostResponse extends PostResponse {
    protected List<CommentInPost> comments;
    protected List<Tag> tags;
    protected String text;
    protected boolean active;

    @Builder
    public FullPostResponse(int id, long time, boolean active, UserInPostResponse user, String title, String announce, String text, int likeCount, int dislikeCount, int commentCount, int viewCount, List<CommentInPost> comments, List<Tag> tags) {
        super(id, time, user, title, announce, likeCount, dislikeCount, commentCount, viewCount);
        this.active = active;
        this.comments = comments;
        this.tags = tags;
        this.text = text;
    }
}
