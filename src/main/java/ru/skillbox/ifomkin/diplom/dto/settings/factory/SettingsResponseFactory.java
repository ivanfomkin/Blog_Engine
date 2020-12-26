package ru.skillbox.ifomkin.diplom.dto.settings.factory;

import ru.skillbox.ifomkin.diplom.model.GlobalSetting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingsResponseFactory {
    public static Map<String, Boolean> getSettingsResponse(List<GlobalSetting> settings) {
        Map<String, Boolean> resultMap = new HashMap<>();
        for (GlobalSetting setting : settings) {
            resultMap.put(setting.getCode(), setting.getValue());
        }
        return resultMap;
    }
}
