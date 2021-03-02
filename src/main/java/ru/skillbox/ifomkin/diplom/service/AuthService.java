package ru.skillbox.ifomkin.diplom.service;

import org.springframework.security.core.Authentication;
import ru.skillbox.ifomkin.diplom.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

public interface AuthService {
    Authentication authenticate(User user, String password);

    Boolean checkAuthorisation(Principal principal);

    void logout(HttpServletRequest request, HttpServletResponse response);
}
