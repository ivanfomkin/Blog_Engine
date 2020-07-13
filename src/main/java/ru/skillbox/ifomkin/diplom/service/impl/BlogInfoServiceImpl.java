package ru.skillbox.ifomkin.diplom.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.skillbox.ifomkin.diplom.service.BlogInfoService;

import java.util.HashMap;
import java.util.Map;

@Service
public class BlogInfoServiceImpl implements BlogInfoService {
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

    @Override
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
}
