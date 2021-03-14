package ru.skillbox.ifomkin.diplom.dto.pofile.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ru.skillbox.ifomkin.diplom.dto.Dto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileEditRequest implements Dto {
    private String name;
    private String email;
    private String password;
    private MultipartFile photo;
    private Integer removePhoto;
}
