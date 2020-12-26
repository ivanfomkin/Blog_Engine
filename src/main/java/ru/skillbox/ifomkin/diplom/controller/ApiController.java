package ru.skillbox.ifomkin.diplom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.ifomkin.diplom.dto.settings.factory.SettingsResponseFactory;
import ru.skillbox.ifomkin.diplom.service.BlogInfoService;
import ru.skillbox.ifomkin.diplom.service.GlobalSettingService;

@RestController
@RequestMapping("api")
public class ApiController {
    private final BlogInfoService blogInfoService;
    private final GlobalSettingService globalSettingService;

    @Autowired
    public ApiController(BlogInfoService blogInfoService, GlobalSettingService globalSettingService) {
        this.blogInfoService = blogInfoService;
        this.globalSettingService = globalSettingService;
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
    public String tags() {
        return "{\n" +
                " \"tags\":\n" +
                " [\n" +
                " {\n" +
                " \"name\": \"PHP\",\n" +
                " \"weight\": 1\n" +
                " },\n" +
                " {\n" +
                " \"name\": \"Python\",\n" +
                " \"weight\": 0.33\n" +
                " }\n" +
                " ]\n" +
                "}\n";
    }

}
