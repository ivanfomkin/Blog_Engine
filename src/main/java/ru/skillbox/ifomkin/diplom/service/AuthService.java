package ru.skillbox.ifomkin.diplom.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

public interface AuthService {
    Authentication authenticate(String email, String password);

    Boolean checkAuthorisation(Principal principal);

    void logout(HttpServletRequest request, HttpServletResponse response);
}
