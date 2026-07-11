package com.foodbridge.foodbridge.controller;

import com.foodbridge.foodbridge.entity.User;
import com.foodbridge.foodbridge.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

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

        return "redirect:/login.html";
    }

    // Open Login Page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login.html";
    }

    // Login Check
    @PostMapping("/login")
    public String loginUser(String email, String password, HttpSession session) {

        System.out.println("Email: " + email);
        System.out.println("Password: " + password);

        User user = userRepository.findByEmailAndPassword(email, password);

        System.out.println(user);

        if (user != null) {

            session.setAttribute("userName", user.getName());
            session.setAttribute("userRole", user.getRole());

            if (user.getRole().equalsIgnoreCase("Admin")) {
                return "redirect:/dashboard";
            }
            else if (user.getRole().equalsIgnoreCase("Donor")) {
                return "redirect:/donate-food.html";
            }
            else if (user.getRole().equalsIgnoreCase("NGO")) {
                return "redirect:/request-food.html";
            }
            else if (user.getRole().equalsIgnoreCase("Volunteer")) {
                return "redirect:/claim-food.html";
            }
        }

        return "redirect:/login.html";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/login.html";
    }

}