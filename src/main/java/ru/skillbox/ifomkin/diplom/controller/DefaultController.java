package ru.skillbox.ifomkin.diplom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DefaultController {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String mainPage() {
        return "index";
    }

    @RequestMapping(value = {
            "/edit/*",
            "/calendar/*",
            "/my/*",
            "/login",
            "/login/**",
            "/moderator/*",
            "/moderation/*",
            "/post/*",
            "/posts/*",
            "/profile",
            "settings",
            "/stat",
            "/404"
    })
    public String all() {
        return "forward:/";
    }
}
