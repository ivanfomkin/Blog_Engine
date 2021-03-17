package ru.skillbox.ifomkin.diplom.service;

import org.springframework.http.ResponseEntity;
import ru.skillbox.ifomkin.diplom.dto.settings.request.ChangeGlobalSettingsRequest;
import ru.skillbox.ifomkin.diplom.model.GlobalSetting;

import java.security.Principal;
import java.util.List;

public interface GlobalSettingService {

    List<GlobalSetting> findAll();

    ResponseEntity<?> changeGlobalSetting(ChangeGlobalSettingsRequest request, Principal principal);

    Boolean getMultiUserModeValue();

    Boolean getPremoderation();
}
