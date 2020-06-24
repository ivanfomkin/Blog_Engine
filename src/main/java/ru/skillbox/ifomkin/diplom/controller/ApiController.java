package ru.skillbox.ifomkin.diplom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.ifomkin.diplom.service.impl.PostServiceImpl;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api")
public class ApiController {
    @Value("${blog.title}")
    private String title;
    @Value("${blog.subtitle}")
    private String subtitle;
    @Value("${blog.phone}")
    private String phone;
    @Value("${blog.email}")
    private String email;
    @Value("${blog.copyright}")
    private String copyright;
    @Value("${blog.copyrightFrom}")
    private String copyrightFrom;

    @GetMapping("/init")
    public Map<String, String> getBlogInfo() {
        Map<String, String> blogInfo = new HashMap<>();
        blogInfo.put("title", title);
        blogInfo.put("subtitle", subtitle);
        blogInfo.put("phone", phone);
        blogInfo.put("email", email);
        blogInfo.put("copyright", copyright);
        blogInfo.put("copyrightFrom", copyrightFrom);
        return blogInfo;
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
