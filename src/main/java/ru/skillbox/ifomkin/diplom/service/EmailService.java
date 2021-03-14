package ru.skillbox.ifomkin.diplom.service;

public interface EmailService {
    void sendRestorePasswordMessage(String email, String hash);
}
