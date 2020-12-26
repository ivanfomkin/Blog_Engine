package ru.skillbox.ifomkin.diplom.service;

import org.springframework.security.core.Authentication;

public interface AuthService {
    Authentication authenticate(String email, String password);
}
