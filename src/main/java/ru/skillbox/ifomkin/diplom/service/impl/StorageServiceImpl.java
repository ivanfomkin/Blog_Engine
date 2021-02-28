package ru.skillbox.ifomkin.diplom.service.impl;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;
import ru.skillbox.ifomkin.diplom.config.StorageConfig;
import ru.skillbox.ifomkin.diplom.exception.IllegalExtensionException;
import ru.skillbox.ifomkin.diplom.exception.IllegalSizeException;
import ru.skillbox.ifomkin.diplom.exception.StorageException;
import ru.skillbox.ifomkin.diplom.service.StorageService;
import ru.skillbox.ifomkin.diplom.utils.RandomStringGenerator;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class StorageServiceImpl implements StorageService {

    private final Path rootLocation;
    @Value("${storage.max-file-size}")
    private DataSize maxFileSize;

    public StorageServiceImpl(StorageConfig storageConfig) {
        this.rootLocation = Paths.get(storageConfig.getUploadPath());
    }

    @Override
    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage location", e);
        }
    }

    @Override
    public String store(MultipartFile image) throws IllegalExtensionException, IOException {
        String uploadFileExtension = FilenameUtils.getExtension(image.getOriginalFilename());
        if (image.getSize() > maxFileSize.toBytes()) {
            throw new IllegalSizeException("Size of file is so big");
        }
        if (!uploadFileExtension.equalsIgnoreCase("jpg") &&
                !uploadFileExtension.equalsIgnoreCase("jpeg") &&
                !uploadFileExtension.equalsIgnoreCase("png") &&
                !uploadFileExtension.equalsIgnoreCase("bmp") &&
                !uploadFileExtension.equalsIgnoreCase("gif")) {
            throw new IllegalExtensionException("Illegal file format");
        }
        String resultFileName = getRandomDirs() + UUID.randomUUID() + image.getOriginalFilename();
        Path resultFilePath = this.rootLocation.resolve(resultFileName);
        try (InputStream inputStream = image.getInputStream()) {
            Files.copy(inputStream, resultFilePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + resultFileName, e);
        }
        return "/" + resultFilePath;
    }

    private String getRandomDirs() throws IOException {
        StringBuilder randomDirs = new StringBuilder();
        for (int i = 0; i < 3; i ++) {
            randomDirs.append(RandomStringGenerator.generateRandomString(2));
            randomDirs.append("/");
        }
        Files.createDirectories(Paths.get(rootLocation.toString() + "/" + randomDirs));
        return randomDirs.toString();
    }
}
