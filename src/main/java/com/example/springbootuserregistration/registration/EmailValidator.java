package com.example.springbootuserregistration.registration;

import org.springframework.stereotype.Service;

@Service
public class EmailValidator {

    public boolean validateEmail(String email) {
        // todo: regex to validate email
        return true;
    }
}
