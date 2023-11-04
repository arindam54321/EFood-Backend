package com.ari.efood.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String subject, String text, List<String> tos, List<String> ccs, List<String> bccs) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        if (tos != null && tos.size() != 0) {
            helper.setTo(tos.toArray(String[]::new));
        }
        if (ccs != null && ccs.size() != 0) {
            helper.setCc(ccs.toArray(String[]::new));
        }
        if (bccs != null && bccs.size() != 0) {
            helper.setBcc(bccs.toArray(String[]::new));
        }

        helper.setSubject(subject);
        helper.setText(text, true);

        javaMailSender.send(message);
    }
}
