package ru.skillbox.ifomkin.diplom.service;

import javax.servlet.http.HttpServletRequest;

public interface EmailService {
    void sendRestorePasswordMessage(String email, String hash, HttpServletRequest servletRequest);

    void sendHelloMessage(String email);

    void sentMessage(String from, String to,
                     String subject, String text);
}
