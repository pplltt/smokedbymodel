package com.example.smokedbymodel.controllers;

import com.example.smokedbymodel.models.User;
import com.example.smokedbymodel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

// Вам понадобится этот импорт:
import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // 1. Временный маршрут для кнопки "Profile" (перенаправляет на ID)
    @GetMapping("/profile")
    public String redirectToUserProfile(Principal principal) {
        if (principal == null) {
            // Если пользователь не вошел, Spring Security перенаправит его на /login
            return "redirect:/login";
        }
        String username = principal.getName();

        // Получаем ID пользователя
        Long userId = userService.findIdByUsername(username);

        // Перенаправляем на новый маршрут с ID
        return "redirect:/user/" + userId;
    }


    // 2. Основной маршрут для отображения профиля
    @GetMapping("/user/{userId}")
    public String userPage(@PathVariable("userId") Long userId, Model model) {

        // ИСПРАВЛЕНИЕ: Используем findById, который активирует @EntityGraph
        User user = userService.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь не найден."));

        // Передача данных в шаблон "user.html"
        model.addAttribute("username", user.getUsername());
        model.addAttribute("reviews", user.getReviews());

        return "user";
    }
}