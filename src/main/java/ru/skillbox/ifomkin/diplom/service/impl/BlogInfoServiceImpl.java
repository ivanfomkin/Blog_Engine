package ru.skillbox.ifomkin.diplom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.skillbox.ifomkin.diplom.dto.statistic.factory.StatisticResponseFactory;
import ru.skillbox.ifomkin.diplom.dto.statistic.response.StatisticResponse;
import ru.skillbox.ifomkin.diplom.model.GlobalSetting;
import ru.skillbox.ifomkin.diplom.model.User;
import ru.skillbox.ifomkin.diplom.repository.GlobalSettingRepository;
import ru.skillbox.ifomkin.diplom.repository.PostRepository;
import ru.skillbox.ifomkin.diplom.repository.PostVotesRepository;
import ru.skillbox.ifomkin.diplom.repository.UserRepository;
import ru.skillbox.ifomkin.diplom.service.BlogInfoService;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Service
public class BlogInfoServiceImpl implements BlogInfoService {

    private final PostRepository postRepository;
    private final PostVotesRepository votesRepository;
    private final GlobalSettingRepository settingRepository;
    private final UserRepository userRepository;

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

    @Autowired
    public BlogInfoServiceImpl(PostRepository postRepository, PostVotesRepository votesRepository, GlobalSettingRepository settingRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.votesRepository = votesRepository;
        this.settingRepository = settingRepository;
        this.userRepository = userRepository;
    }

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

    @Override
    public StatisticResponse getAllStatistic(Principal principal) {
        GlobalSetting statisticsIsPublic =
                settingRepository.findGlobalSettingByCode("STATISTICS_IS_PUBLIC");
        User user = principal == null ? null : userRepository.findByEmail(principal.getName());

        if (statisticsIsPublic.getValue() || (user != null && user.getIsModerator())) {
            return StatisticResponseFactory.buildResponse(settingRepository.getGlobalStats());
        } else {
            return null;
        }
    }
}
