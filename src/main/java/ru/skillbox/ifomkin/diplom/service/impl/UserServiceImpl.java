package ru.skillbox.ifomkin.diplom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;
import ru.skillbox.ifomkin.diplom.dto.pofile.request.ProfileEditRequest;
import ru.skillbox.ifomkin.diplom.dto.pofile.response.ProfileEditErrors;
import ru.skillbox.ifomkin.diplom.dto.pofile.response.ProfileEditResponse;
import ru.skillbox.ifomkin.diplom.dto.security.request.RegisterRequest;
import ru.skillbox.ifomkin.diplom.dto.security.request.RestorePasswordRequest;
import ru.skillbox.ifomkin.diplom.dto.security.response.LoginResponse;
import ru.skillbox.ifomkin.diplom.dto.security.response.RegisterErrorResponse;
import ru.skillbox.ifomkin.diplom.dto.security.response.RegisterResponse;
import ru.skillbox.ifomkin.diplom.model.User;
import ru.skillbox.ifomkin.diplom.repository.CaptchaRepository;
import ru.skillbox.ifomkin.diplom.repository.UserRepository;
import ru.skillbox.ifomkin.diplom.service.EmailService;
import ru.skillbox.ifomkin.diplom.service.StorageService;
import ru.skillbox.ifomkin.diplom.service.UserService;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final StorageService storageService;
    private final EmailService emailService;

    @Value("${storage.max-file-size}")
    private DataSize maxFileSize;
    private final CaptchaRepository captchaRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, StorageService storageService, EmailService emailService, CaptchaRepository captchaRepository) {
        this.userRepository = userRepository;
        this.storageService = storageService;
        this.emailService = emailService;
        this.captchaRepository = captchaRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Boolean isExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public ProfileEditResponse editUser(ProfileEditRequest request, Principal principal, MultipartFile photo) {
        ProfileEditResponse response = new ProfileEditResponse();
        ProfileEditErrors editErrors = new ProfileEditErrors();
        response.setResult(true);

        User user = userRepository.findByEmail(principal.getName());
        if (user == null) {
            response.setResult(false);
        }
        if (request.getEmail() != null) {
            if (userRepository.existsByEmail(request.getEmail())
                    && !user.getEmail().equalsIgnoreCase(request.getEmail())) {
                editErrors.setEmail("Этот email уже зарегестрирован");
                response.setResult(false);
            }
        }
        if (request.getPassword() != null) {
            if (!this.checkValidPassword(request.getPassword())) {
                editErrors.setPassword("Пароль короче 6-ти символов");
                response.setResult(false);
            }
        }
        if (request.getName() != null) {
            if (!this.checkValidUserName(request.getName())) {
                editErrors.setName("Имя введено неправильно");
                response.setResult(false);
            }
        }
        if (photo != null) {
            if (photo.getSize() > maxFileSize.toBytes()) {
                editErrors.setPhoto("Размер фото превышает " + maxFileSize.toMegabytes() + " МБ");
                response.setResult(false);
            }
        }
        if (!response.isResult()) {
            response.setErrors(editErrors);
            return response;
        } else {
            if (request.getName() != null &&
                    !user.getName().equals(request.getName())) {
                user.setName(request.getName());
            }
            if (request.getEmail() != null &&
                    !user.getEmail().equalsIgnoreCase(request.getEmail())) {
                user.setEmail(request.getEmail());
            }
            if (request.getPassword() != null) {
                user.setPassword(new BCryptPasswordEncoder(12).encode(request.getPassword()));
            }
            if (photo != null) {
                try {
                    user.setPhoto(storageService.resizeAndSaveImage(photo));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (request.getName() != null) {
                user.setName(request.getName());
            }
            if (request.getRemovePhoto() != null && request.getRemovePhoto() == 1) {
                user.setPhoto("");
            }
            userRepository.save(user);
        }
        return response;
    }

    @Override
    public RegisterResponse registerUser(RegisterRequest request) {
        RegisterResponse response = new RegisterResponse();
        response.setResult(true);

        RegisterErrorResponse errors = new RegisterErrorResponse();

        if (userRepository.existsByEmail(request.getEmail().toLowerCase())) {
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
            userRepository.save(user);
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

    @Override
    public LoginResponse restorePassword(RestorePasswordRequest request) {
        LoginResponse response = new LoginResponse();
        String email = request.getEmail();
        if (this.isExists(email)) {
            String hash = this.generatePasswordRestoreHash();

            User user = userRepository.findByEmail(email);
            user.setCode(hash);
            userRepository.save(user);

            emailService.sendRestorePasswordMessage(email, hash);
            response.setResult(true);
        } else {
            response.setResult(false);
        }
        return response;
    }

    @Override
    public String generatePasswordRestoreHash() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
