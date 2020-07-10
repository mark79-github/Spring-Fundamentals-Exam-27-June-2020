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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController extends BaseController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public ModelAndView add(Model model,
                            HttpSession httpSession) {

        if (httpSession.getAttribute("user") == null) {
            return new ModelAndView("redirect:/");
//            return "redirect:/";
        }

        if (!model.containsAttribute("productAddBindingModel")) {
            model.addAttribute("productAddBindingModel", new ProductAddBindingModel());
        }

        return new ModelAndView("product-add");
//        return "product-add";
    }

    @PostMapping("/add")
    public ModelAndView addConfirm(@Valid
                             @ModelAttribute(name = "productAddBindingModel") ProductAddBindingModel productAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productAddBindingModel", productAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddBindingModel", bindingResult);
            modelAndView.setViewName("redirect:add");
            return modelAndView;
//            return "redirect:add";
        }

        ProductServiceModel productServiceModel = this.modelMapper.map(productAddBindingModel, ProductServiceModel.class);
        this.productService.addProduct(productServiceModel);

        modelAndView.setViewName("redirect:/");
        return modelAndView;
//        return "redirect:/";
    }

    @GetMapping("/buy")
    public ModelAndView buy(@RequestParam("id") String id,
                      HttpSession httpSession) {

        if (httpSession.getAttribute("user") != null) {
            this.productService.buyProductById(id);
        }

        return new ModelAndView("redirect:/");

//        return "redirect:/";
    }

    @GetMapping("/buy/all")
    public ModelAndView buyAll(HttpSession httpSession) {

        if (httpSession.getAttribute("user") != null) {
            this.productService.buyAllProducts();
        }

        return new ModelAndView("redirect:/");

//        return "redirect:/";
    }

}
