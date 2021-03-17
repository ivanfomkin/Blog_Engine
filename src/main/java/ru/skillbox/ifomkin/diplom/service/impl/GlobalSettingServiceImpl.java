package ru.skillbox.ifomkin.diplom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skillbox.ifomkin.diplom.dto.settings.request.ChangeGlobalSettingsRequest;
import ru.skillbox.ifomkin.diplom.model.GlobalSetting;
import ru.skillbox.ifomkin.diplom.model.User;
import ru.skillbox.ifomkin.diplom.repository.GlobalSettingRepository;
import ru.skillbox.ifomkin.diplom.repository.UserRepository;
import ru.skillbox.ifomkin.diplom.service.GlobalSettingService;

import java.security.Principal;
import java.util.List;

@Service
public class GlobalSettingServiceImpl implements GlobalSettingService {
    private final String MULTIUSER_MODE_CODE = "MULTIUSER_MODE";
    private final String POST_PREMODERATION_CODE = "POST_PREMODERATION";
    private final String STATISTICS_IS_PUBLIC_CODE = "STATISTICS_IS_PUBLIC";
    private final GlobalSettingRepository settingRepository;
    private final UserRepository userRepository;

    @Autowired
    public GlobalSettingServiceImpl(GlobalSettingRepository settingRepository, UserRepository userRepository) {
        this.settingRepository = settingRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<GlobalSetting> findAll() {
        return settingRepository.findAll();
    }

    @Override
    public ResponseEntity<?> changeGlobalSetting(ChangeGlobalSettingsRequest request, Principal principal) {
        User user = principal == null ? null : userRepository.findByEmail(principal.getName());
        if (user == null || !user.getIsModerator()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else {
            settingRepository.updateSettingByCode(MULTIUSER_MODE_CODE,
                    request.getMultiuserMode() ? "YES" : "NO");
            settingRepository.updateSettingByCode(POST_PREMODERATION_CODE,
                    request.getPostPremoderation() ? "YES" : "NO");
            settingRepository.updateSettingByCode(STATISTICS_IS_PUBLIC_CODE,
                    request.getStatisticIsPublic() ? "YES" : "NO");
            return ResponseEntity.ok().build();
        }
    }

    @Override
    public Boolean getMultiUserModeValue() {
        String valueByCode = settingRepository.getValueByCode(MULTIUSER_MODE_CODE);
        return valueByCode.equalsIgnoreCase("YES") ? true : false;
    }

    @Override
    public Boolean getPremoderation() {
        String valueByCode = settingRepository.getValueByCode(POST_PREMODERATION_CODE);
        return valueByCode.equalsIgnoreCase("YES") ? true : false;
    }
}
