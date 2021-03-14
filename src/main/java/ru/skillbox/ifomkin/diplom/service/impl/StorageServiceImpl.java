package ru.skillbox.ifomkin.diplom.service.impl;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;
import ru.skillbox.ifomkin.diplom.config.StorageConfig;
import ru.skillbox.ifomkin.diplom.exception.storage.IllegalExtensionException;
import ru.skillbox.ifomkin.diplom.exception.storage.IllegalSizeException;
import ru.skillbox.ifomkin.diplom.exception.storage.StorageException;
import ru.skillbox.ifomkin.diplom.service.StorageService;
import ru.skillbox.ifomkin.diplom.utils.RandomStringGenerator;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
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
    @Value("${storage.image-width}")
    private Integer imageWIdth;
    @Value("${storage.image-height}")
    private Integer imageHeight;

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
        return saveImage(image.getOriginalFilename(), image.getInputStream());
    }

    private String getRandomDirs() throws IOException {
        StringBuilder randomDirs = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            randomDirs.append(RandomStringGenerator.generateRandomString(2));
            randomDirs.append("/");
        }
        Files.createDirectories(Paths.get(rootLocation.toString() + "/" + randomDirs));
        return randomDirs.toString();
    }

    @Override
    public String resizeAndSaveImage(MultipartFile image) throws IOException {
        //resize
        BufferedImage bufferedImage = ImageIO.read(image.getInputStream());
        BufferedImage resizedImage = Scalr.resize(bufferedImage, imageWIdth, imageHeight);

        //saving
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(resizedImage, FilenameUtils.getExtension(image.getOriginalFilename()), os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());

        return saveImage(image.getOriginalFilename(), is);
    }

    @Override
    public String saveImage(String originalFileName, InputStream inputStream) throws IOException {
        String resultFileName = getRandomDirs() + UUID.randomUUID() + originalFileName;
        Path resultFilePath = this.rootLocation.resolve(resultFileName);
        try (inputStream) {
            Files.copy(inputStream, resultFilePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + resultFileName, e);
        }
        return "/" + resultFilePath;
    }
}
