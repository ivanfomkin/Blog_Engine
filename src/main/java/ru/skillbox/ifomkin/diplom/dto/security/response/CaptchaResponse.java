package ru.skillbox.ifomkin.diplom.dto.security.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skillbox.ifomkin.diplom.dto.Dto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaptchaResponse implements Dto {
    private String secret;
    private String image;
}
