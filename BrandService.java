package com.example.smokedbymodel.controllers;

import com.example.smokedbymodel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String homePage(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            Long userId = userService.findIdByUsername(username);
            model.addAttribute("currentUserId", userId);
        }
        return "home";
    }


}
