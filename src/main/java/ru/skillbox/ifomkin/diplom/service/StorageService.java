package ru.skillbox.ifomkin.diplom.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skillbox.ifomkin.diplom.exception.storage.IllegalExtensionException;

import java.io.IOException;
import java.io.InputStream;

public interface StorageService {

    void init();

    String store(MultipartFile image) throws IllegalExtensionException, IOException;

    String resizeAndSaveImage(MultipartFile image) throws IOException;

    String saveImage(String originalFileName, InputStream inputStream) throws IOException;
}
