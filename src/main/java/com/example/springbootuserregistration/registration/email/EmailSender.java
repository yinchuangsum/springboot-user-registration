package com.example.springbootuserregistration.registration.email;

public interface EmailSender {
    void send(String to, String email);
}
