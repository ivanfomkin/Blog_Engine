package ru.skillbox.ifomkin.diplom.dto.security.response.builder;

import ru.skillbox.ifomkin.diplom.dto.security.response.UserSecurityResponse;
import ru.skillbox.ifomkin.diplom.model.User;
import ru.skillbox.ifomkin.diplom.model.enumerated.Status;
import ru.skillbox.ifomkin.diplom.service.PostService;

public class SecurityUserResponseFactory {
    public static UserSecurityResponse getUserSecurityResponse(User user, PostService postService) {
        if (user == null)
            return null;
        else
            return new UserSecurityResponse(
                    user.getId(),
                    user.getName(),
                    user.getPhoto(),
                    user.getEmail(),
                    user.getIsModerator(),
                    postService.countByModerationStatus(Status.NEW),
                    user.getIsModerator()
            );
    }


}
