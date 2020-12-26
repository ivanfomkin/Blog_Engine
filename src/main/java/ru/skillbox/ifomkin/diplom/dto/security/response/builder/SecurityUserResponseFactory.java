package ru.skillbox.ifomkin.diplom.dto.security.response.builder;

import ru.skillbox.ifomkin.diplom.dto.security.response.UserSecurityResponse;
import ru.skillbox.ifomkin.diplom.model.User;

public class SecurityUserResponseFactory {
    public static UserSecurityResponse getUserSecurityResponse(User user) {
        if (user == null)
            return null;
        else
            return new UserSecurityResponse(
                    user.getId(),
                    user.getName(),
                    user.getPhoto(),
                    user.getEmail(),
                    user.getIsModerator(),
                    user.getModeratedPosts().size(),
                    false
            );
    }
}
