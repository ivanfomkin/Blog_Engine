package ru.skillbox.ifomkin.diplom.dto.post.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skillbox.ifomkin.diplom.dto.comment.response.CommentInPostResponse;
import ru.skillbox.ifomkin.diplom.dto.user.UserInPostResponse;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullPostResponse extends PostResponse {
    protected List<CommentInPostResponse> comments;
    protected List<String> tags;
    protected String text;
    protected boolean active;

    @Builder
    public FullPostResponse(int id, long time, boolean active, UserInPostResponse user, String title, String announce, String text, int likeCount, int dislikeCount, int commentCount, int viewCount, List<CommentInPostResponse> comments, List<String> tags) {
        super(id, time, user, title, announce, likeCount, dislikeCount, commentCount, viewCount);
        this.active = active;
        this.comments = comments;
        this.tags = tags;
        this.text = text;
    }
}
