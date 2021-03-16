package ru.skillbox.ifomkin.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.ifomkin.diplom.model.CaptchaCode;

@Repository
public interface CaptchaRepository extends JpaRepository<CaptchaCode, Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM captcha_codes cc WHERE cc.time < NOW() - :interval * INTERVAL '1' MINUTE", nativeQuery = true)
    void deleteOldCaptcha(@Param("interval") Integer deleteInterval);

    @Query(value = "select upper(:code) = cc.code and cc.time > NOW() - :interval * INTERVAL '1' MINUTE" +
            " from captcha_codes cc where :secret = cc.secret_code",
            nativeQuery = true)
    Boolean checkCaptcha(@Param("code") String code, @Param("secret") String secretCode,
                         @Param("interval") Integer interval);
}
