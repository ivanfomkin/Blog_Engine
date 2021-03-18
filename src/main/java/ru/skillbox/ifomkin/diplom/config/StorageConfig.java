package ru.skillbox.ifomkin.diplom.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.skillbox.ifomkin.diplom.service.StorageService;


@Configuration
public class StorageConfig implements WebMvcConfigurer, CommandLineRunner {
    @Value("${storage.location}")
    @Getter
    private String uploadPath;

    private final StorageService storageService;

    @Autowired
    public StorageConfig(StorageService storageService) {
        this.storageService = storageService;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("upload/**")
                .addResourceLocations("file:" + uploadPath + "/");
    }

    @Override
    public void run(String... args) throws Exception {
        storageService.init();
    }
}


