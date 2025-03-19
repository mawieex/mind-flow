package com.appdev.MindFlow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.appdev.MindFlow.model.User;
import com.appdev.MindFlow.model.VerificationToken;
import com.appdev.MindFlow.repository.VerificationTokenRepository;
import com.appdev.MindFlow.service.UserService;

import java.util.Optional;

@Controller
public class UserController {
    
    @Autowired
    private UserService userService;  
     
    
    @Autowired
    private VerificationTokenRepository verificationRepository;
    @GetMapping("/user/new")
    public String showUserPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/user/save")
    public String saveUserForm(User user, RedirectAttributes redi) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            redi.addFlashAttribute("error", "Username is required!");
            return "redirect:/user/new"; 
        }

        userService.save(user); 
        redi.addFlashAttribute("message", "Registration successful! Please login.");
        return "redirect:/user/login";
    }

    @GetMapping("/user/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/user/authenticate") 
    public String loginUser(@RequestParam("email") String email, 
                            @RequestParam("password") String password, 
                            RedirectAttributes redi) {
        Optional<User> userOpt = userService.findByEmail(email); 
        User user = userOpt.orElse(null);

        if (user != null && user.getPassword().equals(password)) {
            return "redirect:/journal";
        } else {
            redi.addFlashAttribute("error", "Invalid email or password.");
            return "redirect:/user/login";
        }
    }

    @GetMapping("/journal")
    public String showJournalPage() {
        return "journal";
    }

    @GetMapping("/reset")
    public String welcome() {
        return "welcome";
    }
    
    @GetMapping("/forgot-password")
    public String showForgotPasswordPage() {
        return "forgot-password"; 
    }
    
    @PostMapping("/user/forgot-password")
    public String processForgotPassword(@RequestParam("email") String email, Model model) {
        Optional<User> userOpt = userService.findByEmail(email);
        if (userOpt.isPresent()) {
            // Use the service method that accepts a User object.
            String message = userService.generatePasswordResetToken(userOpt.get());
            model.addAttribute("message", message);
        } else {
            model.addAttribute("error", "No account found with that email.");
        }
        return "forgot-password";
    }

    @GetMapping("/user/reset-password")
    public String showResetPage(@RequestParam("token") String token, Model model, RedirectAttributes redi) {
    	Optional<VerificationToken> optionalToken = verificationRepository.findByToken(token);
    	if (!optionalToken.isPresent() || optionalToken.get().isExpired()) {
            redi.addFlashAttribute("error", "Invalid or expired token.");
            return "redirect:/user/login";
        }
        
        model.addAttribute("token", token);

    	return "reset-password"; 
    }
    
    @PostMapping("/user/reset-password")
    public String resetPassword(@RequestParam("token") String token, @RequestParam("newPassword") String newPassword) {
        return "redirect:/user/login"; 
    }
}
