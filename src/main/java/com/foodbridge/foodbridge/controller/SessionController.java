package com.foodbridge.foodbridge.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

    @GetMapping("/checkLogin")
    public boolean checkLogin(HttpSession session) {

        return session.getAttribute("userName") != null;

    }
}