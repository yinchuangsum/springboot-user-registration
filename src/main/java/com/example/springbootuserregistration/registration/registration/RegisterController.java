package com.example.springbootuserregistration.registration.registration;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/register")
@RequiredArgsConstructor
public class RegisterController {
    private final RegistrationService registrationService;

    @PostMapping
    @ResponseBody
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
