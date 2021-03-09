package ru.skillbox.ifomkin.diplom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.ifomkin.diplom.dto.settings.factory.SettingsResponseFactory;
import ru.skillbox.ifomkin.diplom.dto.tag.factory.TagResponseFactory;
import ru.skillbox.ifomkin.diplom.service.BlogInfoService;
import ru.skillbox.ifomkin.diplom.service.GlobalSettingService;
import ru.skillbox.ifomkin.diplom.service.TagService;

@RestController
@RequestMapping("api")
public class ApiController {
    private final BlogInfoService blogInfoService;
    private final GlobalSettingService globalSettingService;
    private final TagService tagService;

    @Autowired
    public ApiController(BlogInfoService blogInfoService, GlobalSettingService globalSettingService, TagService tagService) {
        this.blogInfoService = blogInfoService;
        this.globalSettingService = globalSettingService;
        this.tagService = tagService;
    }

    @GetMapping("/init")
    public ResponseEntity<?> getBlogInfo() {
        return ResponseEntity.ok(blogInfoService.getBlogInfo());
    }

    @GetMapping("/settings")
    public ResponseEntity<?> getBlogSettings() {
        return ResponseEntity.ok(
                SettingsResponseFactory.getSettingsResponse(globalSettingService.findAll()));
    }

    @GetMapping("/tag")
    public ResponseEntity<?> getTags() {
        return ResponseEntity.ok(
                TagResponseFactory.buildTagResponse(tagService.getTagMapWithWeight())
        );
    }
}
