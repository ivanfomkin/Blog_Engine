package ru.skillbox.ifomkin.diplom.dto.file.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skillbox.ifomkin.diplom.dto.Dto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadErrorResponse implements Dto {
    private String image;
}
