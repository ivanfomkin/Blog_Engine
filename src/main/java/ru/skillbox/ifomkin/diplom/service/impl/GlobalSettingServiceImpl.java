package ru.skillbox.ifomkin.diplom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.ifomkin.diplom.model.GlobalSetting;
import ru.skillbox.ifomkin.diplom.repository.GlobalSettingRepository;
import ru.skillbox.ifomkin.diplom.service.GlobalSettingService;

import java.util.List;

@Service
public class GlobalSettingServiceImpl implements GlobalSettingService {
    private final GlobalSettingRepository repository;

    @Autowired
    public GlobalSettingServiceImpl(GlobalSettingRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<GlobalSetting> findAll() {
        return repository.findAll();
    }
}
