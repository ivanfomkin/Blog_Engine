package ru.skillbox.ifomkin.diplom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private final String REGISTRATION_SUBJECT = "DevPub: Спасибо за регистрацию!";

    @Value("${blog.title}")
    private String blogTitle;
    @Value("${spring.mail.address}")
    private String noReplyEmailAddress;

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendRestorePasswordMessage(String email, String hash) {
        StringBuilder messageText = new StringBuilder();
        try {
            messageText.append("Добрый день! \n")
                    .append("Вы запросили воссстановление паароля на портале ")
                    .append(blogTitle)
                    .append("\n Для восстановления пароля передите по ссылке ниже: \n")
                    .append("http://")
                    .append(InetAddress.getLocalHost().getHostAddress())
                    .append(":8080/login/change-password/")
                    .append(hash)
                    .append("\n С наилучшими пожеланиями, команда ")
                    .append(blogTitle);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        sentMessage(noReplyEmailAddress, email, PASSWORD_RESTORE_SUBJECT, messageText.toString());
    }

    @Override
    public void sendHelloMessage(String email) {
        StringBuilder messageText = new StringBuilder();
        messageText.append("Добрый день! \n")
                .append("Вы зарегистрировались на портале ")
                .append(blogTitle)
                .append("\nРади приветствовать Вас! \n")
                .append("\n С наилучшими пожеланиями, команда ")
                .append(blogTitle);
        sentMessage(noReplyEmailAddress, email, REGISTRATION_SUBJECT, messageText.toString());

    }

    @Override
    public void sentMessage(String from, String to,
                            String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
