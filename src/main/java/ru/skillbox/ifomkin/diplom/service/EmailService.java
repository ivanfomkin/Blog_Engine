package ru.skillbox.ifomkin.diplom.service;

public interface EmailService {
    void sendRestorePasswordMessage(String email, String hash);

    void sendHelloMessage(String email);

    void sentMessage(String from, String to,
                     String subject, String text);
}
