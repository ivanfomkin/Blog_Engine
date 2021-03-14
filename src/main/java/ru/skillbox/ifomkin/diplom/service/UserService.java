package ru.skillbox.ifomkin.diplom.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skillbox.ifomkin.diplom.dto.Dto;
import ru.skillbox.ifomkin.diplom.dto.pofile.request.ProfileEditRequest;
import ru.skillbox.ifomkin.diplom.dto.pofile.response.ProfileEditResponse;
import ru.skillbox.ifomkin.diplom.dto.security.request.RegisterRequest;
import ru.skillbox.ifomkin.diplom.dto.security.request.RestorePasswordRequest;
import ru.skillbox.ifomkin.diplom.dto.security.response.LoginResponse;
import ru.skillbox.ifomkin.diplom.dto.security.response.RegisterResponse;
import ru.skillbox.ifomkin.diplom.model.User;

import java.security.Principal;
import java.util.List;

public interface UserService {
    List<User> getAll();

    Boolean isExists(String email);

    User findByEmail(String email);

    ProfileEditResponse editUser(ProfileEditRequest request, Principal principal, MultipartFile photo);

    RegisterResponse registerUser(RegisterRequest request);

    Boolean checkValidUserName(String username);

    Boolean checkValidPassword(String password);

    LoginResponse restorePassword(RestorePasswordRequest request);

    String generatePasswordRestoreHash();
}
