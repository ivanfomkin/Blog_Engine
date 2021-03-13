package ru.skillbox.ifomkin.diplom.dto.post.factory;

import ru.skillbox.ifomkin.diplom.dto.post.response.ModeratePostResponse;

public class ModerateResponseFactory {
    public static ModeratePostResponse buildResponse(boolean result) {
        return new ModeratePostResponse(result);
    }
}
