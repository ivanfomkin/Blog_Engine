package ru.skillbox.ifomkin.diplom.dto.file.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skillbox.ifomkin.diplom.dto.Dto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadResult implements Dto {
    private Boolean result;
    private FileUploadErrorResponse errors;
}
