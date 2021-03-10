package ru.skillbox.ifomkin.diplom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.ifomkin.diplom.dto.calendar.factory.CalendarResponseFactory;
import ru.skillbox.ifomkin.diplom.dto.settings.factory.SettingsResponseFactory;
import ru.skillbox.ifomkin.diplom.dto.tag.factory.TagResponseFactory;
import ru.skillbox.ifomkin.diplom.service.BlogInfoService;
import ru.skillbox.ifomkin.diplom.service.GlobalSettingService;
import ru.skillbox.ifomkin.diplom.service.PostService;
import ru.skillbox.ifomkin.diplom.service.TagService;

@RestController
@RequestMapping("api")
public class ApiController {
    private final BlogInfoService blogInfoService;
    private final GlobalSettingService globalSettingService;
    private final TagService tagService;
    private final PostService postService;

    @Autowired
    public ApiController(BlogInfoService blogInfoService, GlobalSettingService globalSettingService, TagService tagService, PostService postService) {
        this.blogInfoService = blogInfoService;
        this.globalSettingService = globalSettingService;
        this.tagService = tagService;
        this.postService = postService;
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
    public ResponseEntity<?> getTags(
            @RequestParam(required = false, defaultValue = "") String query
    ) {
        return ResponseEntity.ok(
                TagResponseFactory.buildTagResponse(tagService.getTagsWithWeight(query))
        );
    }

    @GetMapping("/calendar")
    public ResponseEntity<?> getCalendar(
            @RequestParam(required = false, defaultValue = "") String year
    ) {
        return ResponseEntity.ok(CalendarResponseFactory.buildCalendarResponse(
                postService.getYearsWithActivePost(),
                postService.getPostCountByDateFromDb(year)
        ));
    }
}
