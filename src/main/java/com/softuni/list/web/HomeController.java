package com.softuni.list.web;

import com.softuni.list.service.CategoryService;
import com.softuni.list.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

        if (httpSession.getAttribute("user") == null) {
            return super.view("index");
//            modelAndView.vi("index");
//            return "index";
        } else {
            return super.redirect("home");
//            modelAndView.setViewName("redirect:home");
//            return "redirect:home";
        }

    }

    @GetMapping("/home")
    public ModelAndView home(Model model,
                             HttpSession httpSession,
                             ModelAndView modelAndView) {

        if (httpSession.getAttribute("user") == null) {
            return super.redirect("/");
//            modelAndView.setViewName("redirect:/");
//            return modelAndView;
//            return "redirect:/";
        } else {

            modelAndView.addObject("categories", this.categoryService.getAllCategories());
//        model.addAttribute("categories", this.categoryService.getAllCategories());
            modelAndView.addObject("productsTotalPrice", this.productService.getProductsTotalPrice());
//        model.addAttribute("productsTotalPrice", this.productService.getProductsTotalPrice());

//        return "home";

            return super.view("home", modelAndView);
//            modelAndView.setViewName("home");
//            return modelAndView;
        }
    }
}
