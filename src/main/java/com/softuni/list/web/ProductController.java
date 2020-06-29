package com.softuni.list.web;

import com.softuni.list.model.bind.ProductAddBindingModel;
import com.softuni.list.model.service.ProductServiceModel;
import com.softuni.list.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add(Model model,
                      HttpSession httpSession) {

        if (httpSession.getAttribute("user") == null) {
            return "redirect:/";
        }

        if (!model.containsAttribute("productAddBindingModel")) {
            model.addAttribute("productAddBindingModel", new ProductAddBindingModel());
        }

        return "product-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid
                             @ModelAttribute(name = "productAddBindingModel") ProductAddBindingModel productAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productAddBindingModel", productAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddBindingModel", bindingResult);
            return "redirect:add";
        }

        ProductServiceModel productServiceModel = this.modelMapper.map(productAddBindingModel, ProductServiceModel.class);
        this.productService.addProduct(productServiceModel);

        return "redirect:/";
    }

    @GetMapping("/buy")
    public String buy(@RequestParam("id") String id,
                      HttpSession httpSession) {

        if (httpSession.getAttribute("user") != null) {
            this.productService.buyProductById(id);
        }

        return "redirect:/";
    }

    @GetMapping("/buy/all")
    public String buyAll(HttpSession httpSession) {

        if (httpSession.getAttribute("user") != null) {
            this.productService.buyAllProducts();
        }

        return "redirect:/";
    }

}
