package com.example.springbootuserregistration.registration.registration;

import com.example.springbootuserregistration.model.User;
import com.example.springbootuserregistration.registration.EmailValidator;
import com.example.springbootuserregistration.registration.email.EmailSender;
import com.example.springbootuserregistration.registration.token.ConfirmationToken;
import com.example.springbootuserregistration.registration.token.ConfirmationTokenService;
import com.example.springbootuserregistration.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.validateEmail(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }
        return userService.signUpUser(new User(null, request.getName(), request.getUsername(), request.getEmail(), request.getPassword(), false));
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.findConfirmationTokenByToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiredAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationToken.setConfirmedAt(LocalDateTime.now());

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        User user = userService.findById(confirmationToken.getUserId()).get();
        user.setEnabled(true);
        userService.saveUser(user);
        return "confirmed";
    }
}
