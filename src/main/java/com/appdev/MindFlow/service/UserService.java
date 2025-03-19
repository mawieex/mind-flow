package com.appdev.MindFlow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.appdev.MindFlow.model.User;
import com.appdev.MindFlow.model.VerificationToken;
import com.appdev.MindFlow.repository.UserRepository;
import com.appdev.MindFlow.repository.VerificationTokenRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; // ✅ Inject UserRepository
    @Autowired
    private VerificationTokenRepository verificationRepository;
    @Autowired
    private EmailService emailService; // ✅ Inject EmailService

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email); // ✅ FIX: Use instance, not static call
    }

    public String generatePasswordResetToken(User user) {
        if (user != null) {
            String token = UUID.randomUUID().toString(); // Generate a unique token

            // Create and save the token for later verification
            VerificationToken verificationToken = new VerificationToken(token, user);
            verificationRepository.save(verificationToken);

            // Send the password reset email using the injected emailService
            emailService.sendPasswordResetEmail(user.getEmail(), token);

            return "Password reset link sent to your email.";
        }
        return "User not found.";
    }


    public void save(User user) {
        userRepository.save(user); // ✅ Save user details
    }
}
