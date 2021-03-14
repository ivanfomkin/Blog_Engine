package ru.skillbox.ifomkin.diplom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.skillbox.ifomkin.diplom.service.EmailService;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private final String PASSWORD_RESTORE_SUBJECT = "DevPub: Восстановление пароля";

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendRestorePasswordMessage(String email, String hash) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("d3vpub@yandex.ru");
        message.setTo(email);
        message.setSubject(PASSWORD_RESTORE_SUBJECT);
        StringBuilder messageText = new StringBuilder();
        try {
            messageText.append("Добрый день! \n")
                    .append("Вы запросили воссстановление паароля на сайте ")
                    .append(InetAddress.getLocalHost().getHostAddress())
                    .append("\n Для восстановления пароля передите по ссылке ниже: \n")
                    .append("<a href=\"http://")
                    .append(InetAddress.getLocalHost().getHostAddress())
                    .append(":8080/login/change-password/")
                    .append(hash)
                    .append("\"> \n С наилучшими пожеланиями, команда DevPub!");
            message.setText(messageText.toString());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }
}
