package com.example.springbootuserregistration.registration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {
    @Value(value = "${mail.smtp.host}")
    private String smtpHost;

    @Value(value = "${mail.smtp.port}")
    private String smtpPort;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        Properties p = new Properties();
        p.setProperty("mail.smtp.auth", "false");
        p.setProperty("mail.smtp.host", smtpHost);
        p.setProperty("mail.smtp.port", smtpPort);
        mailSender.setJavaMailProperties(p);

        return mailSender;
    }
}
