package ru.skillbox.ifomkin.diplom.dto.pofile.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skillbox.ifomkin.diplom.dto.Dto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileEditErrors implements Dto {
    private String email;
    private String photo;
    private String name;
    private String password;
}
