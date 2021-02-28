package ru.skillbox.ifomkin.diplom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.skillbox.ifomkin.diplom.dto.file.factory.FileUploadResultFactory;
import ru.skillbox.ifomkin.diplom.service.StorageService;

@RestController
@RequestMapping("api/image")
public class UploadFileController {
    private final StorageService storageService;

    @Autowired
    public UploadFileController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile image) {
        try {
            return ResponseEntity.ok(storageService.store(image));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(FileUploadResultFactory.getFileUploadResult(e));
        }
    }
}
