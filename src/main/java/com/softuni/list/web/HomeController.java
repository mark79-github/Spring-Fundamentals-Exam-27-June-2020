package com.softuni.list.web;

import com.softuni.list.service.CategoryService;
import com.softuni.list.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public HomeController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String index(HttpSession httpSession) {

        if (httpSession.getAttribute("user") == null) {
            return "index";
        } else {
            return "redirect:home";
        }

    }

    @GetMapping("/home")
    public String home(Model model,
                       HttpSession httpSession) {

        if (httpSession.getAttribute("user") == null) {
            return "redirect:/";
        }

        model.addAttribute("categories", this.categoryService.getAllCategories());
        model.addAttribute("totalPriceOfProducts", this.productService.getProductsTotalPrice());

        return "home";
    }
}
