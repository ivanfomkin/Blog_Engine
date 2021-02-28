package ru.skillbox.ifomkin.diplom.dto.file.factory;

import ru.skillbox.ifomkin.diplom.dto.file.response.FileUploadErrorResponse;
import ru.skillbox.ifomkin.diplom.dto.file.response.FileUploadResult;
import ru.skillbox.ifomkin.diplom.exception.IllegalExtensionException;
import ru.skillbox.ifomkin.diplom.exception.IllegalSizeException;

public class FileUploadResultFactory {
    public static FileUploadResult getFileUploadResult(Exception e) {
        FileUploadErrorResponse fileUploadErrorResponse = new FileUploadErrorResponse();
        if (e instanceof IllegalExtensionException) {
            fileUploadErrorResponse.setImage("Неверное расширение файла, допустимы только изображения");
        } else if (e instanceof IllegalSizeException) {
            fileUploadErrorResponse.setImage("Размер файла превышает допустимый размер");
        } else {
            fileUploadErrorResponse.setImage("Неизвестный тип ошибки: " + e.getMessage());
        }
        return new FileUploadResult(false, fileUploadErrorResponse);
    }
}
