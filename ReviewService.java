package com.example.smokedbymodel.controllers;

import com.example.smokedbymodel.models.Brand;
import com.example.smokedbymodel.models.Review;
import com.example.smokedbymodel.services.BrandService;
import com.example.smokedbymodel.services.ReviewService;
import com.example.smokedbymodel.services.UserService;
import com.example.smokedbymodel.dto.ReviewForm;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@Controller
public class ReviewController {

    @Autowired private BrandService brandService;
    @Autowired private UserService userService;
    @Autowired private ReviewService reviewService;

    @GetMapping("/brands/{brandId}/reviews/new")
    public String showReviewForm(@PathVariable("brandId") Long brandId, Model model) {

        Brand brand = brandService.findById(brandId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Brand not found."));

        model.addAttribute("brand", brand);
        model.addAttribute("reviewForm", new ReviewForm());

        return "review_form"; // Название шаблона
    }

    @PostMapping("/brands/{brandId}/reviews")
    public String submitReview(@PathVariable("brandId") Long brandId,
                               @Valid @ModelAttribute("reviewForm") ReviewForm form,
                               BindingResult result,
                               Principal principal,
                               Model model) {

        if (result.hasErrors()) {
            Brand brand = brandService.findById(brandId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Brand not found."));
            model.addAttribute("brand", brand);

            return "review_form";
        }

        String username = principal.getName();

        reviewService.saveNewReview(form, username, brandId);

        return "redirect:/brands/" + brandId;
    }

    @GetMapping("/brands/{brandId}")
    public String viewBrandReviews(@PathVariable("brandId") Long brandId, Model model) {

        Brand brand = brandService.findById(brandId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Brand not found."));

        List<Review> reviews = reviewService.findReviewsByBrandId(brandId);

        double averageRating = reviewService.getAverageRating(reviews);

        model.addAttribute("brand", brand);
        model.addAttribute("reviews", reviews);
        // Форматируем средний рейтинг до двух знаков после запятой
        model.addAttribute("averageRating", String.format("%.1f", averageRating));
        model.addAttribute("reviewCount", reviews.size());

        return "brand_reviews";
    }
}