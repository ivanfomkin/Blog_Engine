package ru.skillbox.ifomkin.diplom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataSize;
import ru.skillbox.ifomkin.diplom.dto.pofile.request.ProfileEditRequest;
import ru.skillbox.ifomkin.diplom.dto.pofile.response.ProfileEditErrors;
import ru.skillbox.ifomkin.diplom.dto.pofile.response.ProfileEditResponse;
import ru.skillbox.ifomkin.diplom.model.User;
import ru.skillbox.ifomkin.diplom.repository.UserRepository;
import ru.skillbox.ifomkin.diplom.service.StorageService;
import ru.skillbox.ifomkin.diplom.service.UserService;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final StorageService storageService;

    @Value("${storage.max-file-size}")
    private DataSize maxFileSize;

    @Autowired
    public UserServiceImpl(UserRepository repository, StorageService storageService) {
        this.repository = repository;
        this.storageService = storageService;
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
    public ProfileEditResponse editUser(ProfileEditRequest request, Principal principal) {
        ProfileEditResponse response = new ProfileEditResponse();
        ProfileEditErrors editErrors = new ProfileEditErrors();
        response.setResult(true);

        User user = repository.findByEmail(principal.getName());
        if (user == null) {
            response.setResult(false);
        }
        if (request.getEmail() != null) {
            if (repository.existsByEmail(request.getEmail())
                    && !user.getEmail().equalsIgnoreCase(request.getEmail())) {
                editErrors.setEmail("Этот email уже зарегестрирован");
                response.setResult(false);
            }
        }
        if (request.getPassword() != null) {
            if (request.getPassword().length() < 6) {
                editErrors.setPassword("Пароль короче 6-ти символов");
                response.setResult(false);
            }
        }
        if (request.getPhoto() != null) {
            if (request.getPhoto().getSize() > maxFileSize.toBytes()) {
                editErrors.setPhoto("Размер фото превышает " + maxFileSize.toMegabytes() + " МБ");
                response.setResult(false);
            }
        }
        // @Todo: Check valid username
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
                user.setPassword(request.getPassword());
            }
            if (request.getPhoto() != null) {
                try {
                    user.setPhoto(storageService.resizeAndSaveImage(request.getPhoto()));
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
            repository.save(user);
        }
        return response;
    }
}
