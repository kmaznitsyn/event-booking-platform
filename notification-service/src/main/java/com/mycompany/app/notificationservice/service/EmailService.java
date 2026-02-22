package com.mycompany.app.notificationservice.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender sender;


    public EmailService(JavaMailSender sender) {
        this.sender = sender;
    }
}
