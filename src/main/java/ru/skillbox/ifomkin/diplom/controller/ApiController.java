package ru.skillbox.ifomkin.diplom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.ifomkin.diplom.dto.calendar.factory.CalendarResponseFactory;
import ru.skillbox.ifomkin.diplom.dto.post.factory.ModerateResponseFactory;
import ru.skillbox.ifomkin.diplom.dto.post.request.ModeratePostRequest;
import ru.skillbox.ifomkin.diplom.dto.settings.factory.SettingsResponseFactory;
import ru.skillbox.ifomkin.diplom.dto.tag.factory.TagResponseFactory;
import ru.skillbox.ifomkin.diplom.service.*;

import java.security.Principal;

@RestController
@RequestMapping("api")
public class ApiController {
    private final BlogInfoService blogInfoService;
    private final GlobalSettingService globalSettingService;
    private final TagService tagService;
    private final PostService postService;
    private final UserService userService;

    @Autowired
    public ApiController(BlogInfoService blogInfoService, GlobalSettingService globalSettingService, TagService tagService, PostService postService, UserService userService) {
        this.blogInfoService = blogInfoService;
        this.globalSettingService = globalSettingService;
        this.tagService = tagService;
        this.postService = postService;
        this.userService = userService;
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

    @PreAuthorize("hasAuthority('user:moderate')")
    @PostMapping("/moderation")
    public ResponseEntity<?> moderatePost(
            @RequestBody ModeratePostRequest request,
            Principal principal
    ) {
        return ResponseEntity.ok(
                ModerateResponseFactory.buildResponse(
                        postService.moderatePost(request, principal)));
    }

    @PreAuthorize("hasAuthority('user:write')")
    @GetMapping("/statistics/my")
    public ResponseEntity<?> myStatistic(Principal principal) {
        return ResponseEntity.ok(userService.getMyStatistic(principal));
    }
}
