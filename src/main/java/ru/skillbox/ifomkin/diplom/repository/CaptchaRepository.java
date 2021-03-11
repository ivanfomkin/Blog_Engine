package ru.skillbox.ifomkin.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillbox.ifomkin.diplom.model.CaptchaCode;

@Repository
public interface CaptchaRepository extends JpaRepository<CaptchaCode, Long> {
}
