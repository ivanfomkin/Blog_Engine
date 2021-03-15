package ru.skillbox.ifomkin.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillbox.ifomkin.diplom.model.GlobalSetting;

import java.util.List;

@Repository
public interface GlobalSettingRepository extends JpaRepository<GlobalSetting, Integer> {
    List<GlobalSetting> findAll();

    GlobalSetting findGlobalSettingByCode(String code);
}
