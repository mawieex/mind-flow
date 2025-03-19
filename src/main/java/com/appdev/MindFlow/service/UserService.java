package com.appdev.MindFlow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // ✅ Import Transactional
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

    @Transactional // ✅ Ensures password changes are persisted
    public void save(User user) {
        System.out.println("Saving User: " + user.getEmail());
        System.out.println("New Password to be Saved: " + user.getPassword());

        userRepository.save(user);

        // Fetch again to confirm password update
        Optional<User> updatedUser = userRepository.findByEmail(user.getEmail());
        if (updatedUser.isPresent()) {
            System.out.println("Updated Password in DB: " + updatedUser.get().getPassword());
        } else {
            System.out.println("User not found after update!");
        }
    }
}
