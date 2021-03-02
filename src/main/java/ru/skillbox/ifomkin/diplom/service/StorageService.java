package ru.skillbox.ifomkin.diplom.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skillbox.ifomkin.diplom.exception.storage.IllegalExtensionException;

import java.io.IOException;

public interface StorageService {

    void init();

    String store(MultipartFile image) throws IllegalExtensionException, IOException;
}
