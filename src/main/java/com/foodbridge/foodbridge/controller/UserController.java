package com.foodbridge.foodbridge.controller;

import com.foodbridge.foodbridge.entity.User;
import com.foodbridge.foodbridge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Open Register Page
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register.html";
    }

    // Save Register Data
    @PostMapping("/register")
    public String registerUser(User user) {
        userRepository.save(user);
        return "redirect:/dashboard";
    }

    // Open Login Page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login.html";
    }

    // Login Submit
    @PostMapping("/login")
    public String loginUser() {
        return "dashboard.html";
    }

    // Logout Page
    @GetMapping("/logout")
    public String logout() {
        return "logout.html";
    }
}