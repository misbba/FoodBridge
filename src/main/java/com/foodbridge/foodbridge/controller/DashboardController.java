package com.foodbridge.foodbridge.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DashboardController {

    @GetMapping("/username")
    @ResponseBody
    public String username(HttpSession session) {

        Object name = session.getAttribute("userName");

        if (name == null) {
            return "";
        }

        return name.toString();
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {

        if (session.getAttribute("userName") == null) {
            return "redirect:/login.html";
        }

        return "dashboard.html";
    }

}