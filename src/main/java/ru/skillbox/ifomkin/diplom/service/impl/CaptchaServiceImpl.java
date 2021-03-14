package ru.skillbox.ifomkin.diplom.service.impl;

import com.github.cage.Cage;
import com.github.cage.GCage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.skillbox.ifomkin.diplom.dto.security.response.CaptchaResponse;
import ru.skillbox.ifomkin.diplom.model.CaptchaCode;
import ru.skillbox.ifomkin.diplom.repository.CaptchaRepository;
import ru.skillbox.ifomkin.diplom.service.CaptchaService;
import ru.skillbox.ifomkin.diplom.utils.RandomStringGenerator;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;

@Service
@EnableScheduling
public class CaptchaServiceImpl implements CaptchaService {
    private final CaptchaRepository captchaRepository;
    private final String DATA_TYPE_PREFIX = "data:image/png;base64, ";

    @Value("${captcha.length}")
    private Integer captchaLength;
    @Value("${captcha.drop-time}")
    private Integer dropTime;

    @Autowired
    public CaptchaServiceImpl(CaptchaRepository captchaRepository) {
        this.captchaRepository = captchaRepository;
    }

    @Override
    public CaptchaResponse getCaptchaResponse() {
        Cage cage = new GCage();
        CaptchaCode captchaCode = new CaptchaCode();
        CaptchaResponse captchaResponse = new CaptchaResponse();

        String codeOnImage = RandomStringGenerator.generateUpperCaseAndNumbersString(captchaLength);
        String secretCode = cage.getTokenGenerator().next();
        String base64Image = Base64.getEncoder().encodeToString(cage.draw(codeOnImage));

        captchaCode.setCode(codeOnImage);
        captchaCode.setSecretCode(secretCode);
        captchaCode.setTime(LocalDateTime.now(ZoneId.systemDefault()));
        captchaRepository.save(captchaCode);

        captchaResponse.setSecret(secretCode);
        captchaResponse.setImage(DATA_TYPE_PREFIX + base64Image);

        return captchaResponse;
    }

    @Override
    @Scheduled(cron = "0 */30 * ? * *")
    public void deleteOldCaptcha() {
        captchaRepository.deleteOldCaptcha(dropTime);
    }
}
