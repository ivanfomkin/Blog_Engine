package ru.skillbox.ifomkin.diplom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.ifomkin.diplom.service.BlogInfoService;

import java.util.Map;

@RestController
@RequestMapping("api")
public class ApiController {
    private final BlogInfoService blogInfoService;

    @Autowired
    public ApiController(BlogInfoService blogInfoService) {
        this.blogInfoService = blogInfoService;
    }

    @GetMapping("/init")
    public Map<String, String> getBlogInfo() {
        return blogInfoService.getBlogInfo();
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
