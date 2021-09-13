package com.example.springbootuserregistration.registration.registration;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistrationRequest {
    private String name;
    private String username;
    private String email;
    private String password;
}
