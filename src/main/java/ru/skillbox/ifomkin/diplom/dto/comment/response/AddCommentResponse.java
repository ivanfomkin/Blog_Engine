package ru.skillbox.ifomkin.diplom.dto.comment.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddCommentResponse {
    private Integer id;
    private Boolean result;
    private AddCommentErrorResponse errors;
}
