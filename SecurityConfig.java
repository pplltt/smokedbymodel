package com.example.smokedbymodel.controllers;

import com.example.smokedbymodel.models.Brand;
import com.example.smokedbymodel.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/brands")
    public String selectBrand(@RequestParam(value = "search", required = false) String searchQuery, Model model) {
        List<Brand> brands = brandService.searchBrands(searchQuery);
        model.addAttribute("brands", brands);
        model.addAttribute("searchQuery", searchQuery == null ? "" : searchQuery);
        return "brands";
    }


}
