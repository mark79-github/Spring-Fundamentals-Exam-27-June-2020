package com.softuni.list.web;

import com.softuni.list.service.CategoryService;
import com.softuni.list.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController extends BaseController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public HomeController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public ModelAndView index(HttpSession httpSession) {

        return httpSession.getAttribute("user") == null ? view("index") : redirect("home");
    }

    @GetMapping("/home")
    public ModelAndView home(HttpSession httpSession,
                             ModelAndView modelAndView) {

        if (httpSession.getAttribute("user") == null) {
            return redirect("/");
        }

        modelAndView.addObject("categories", this.categoryService.getAllCategories());
        modelAndView.addObject("productsTotalPrice", this.productService.getProductsTotalPrice());

        return view("home", modelAndView);
    }
}
