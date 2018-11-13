package com.ubosque.sgdaubosque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailContentBuilder mailContentBuilder;

    public String sendMail(String to, String message) throws Exception {
        // SimpleMailMessage message = new SimpleMailMessage();
        // message.setText("Hello from Spring Boot Application");
        // message.setTo("sergioportilla98@gmail.com");
        // message.setFrom("no-reply@gmail.com");
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("no-reply@gmail.com");
            messageHelper.setTo(to);
            messageHelper.setSubject("Tienes una Radicacion Nueva");
            String content = mailContentBuilder.build(message);
            messageHelper.setText(content, true);
        };
        // mailSender.send(mail);

        mailSender.send(messagePreparator);
        return "Email enviado!";

    }
}