package ru.skillbox.ifomkin.diplom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skillbox.ifomkin.diplom.dto.security.request.RegisterRequest;
import ru.skillbox.ifomkin.diplom.dto.security.response.RegisterErrorResponse;
import ru.skillbox.ifomkin.diplom.dto.security.response.RegisterResponse;
import ru.skillbox.ifomkin.diplom.model.User;
import ru.skillbox.ifomkin.diplom.repository.CaptchaRepository;
import ru.skillbox.ifomkin.diplom.repository.UserRepository;
import ru.skillbox.ifomkin.diplom.service.UserService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final CaptchaRepository captchaRepository;

    @Autowired
    public UserServiceImpl(UserRepository repository, CaptchaRepository captchaRepository) {
        this.repository = repository;
        this.captchaRepository = captchaRepository;
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public Boolean isExists(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public RegisterResponse registerUser(RegisterRequest request) {
        RegisterResponse response = new RegisterResponse();
        response.setResult(true);

        RegisterErrorResponse errors = new RegisterErrorResponse();

        if (repository.existsByEmail(request.getEmail().toLowerCase())) {
            response.setResult(false);
            errors.setEmail("Пользователь с таким email уже зарегистрирован");
        }
        if (!captchaRepository.checkCaptcha(request.getCaptcha().toUpperCase()
                , request.getCaptchaSecret())) {
            response.setResult(false);
            errors.setCaptcha("Код с картинки введён неверно");
        }
        if (!checkValidUserName(request.getName())) {
            response.setResult(false);
            errors.setName("Имя указан неверно");
        }
        if (!checkValidPassword(request.getPassword())) {
            response.setResult(false);
            errors.setPassword("Пароль короче 6 символов");
        }

        if (response.isResult()) {
            User user = new User();
            user.setEmail(request.getEmail().toLowerCase());
            user.setName(request.getName());
            user.setRegTime(LocalDateTime.now(ZoneId.systemDefault()));
            user.setPassword(new BCryptPasswordEncoder(12).encode(request.getPassword()));
            user.setIsModerator(false);
            repository.save(user);
        } else {
            response.setErrors(errors);
        }

        return response;
    }

    @Override
    public Boolean checkValidUserName(String username) {
        return username != null
                && username.length() >= 4
                && username.length() <= 30
                && username.matches("[^\\d][а-яА-Я0-9a-zA-Z]*");
    }

    @Override
    public Boolean checkValidPassword(String password) {
        return password.length() >= 6;
    }
}
