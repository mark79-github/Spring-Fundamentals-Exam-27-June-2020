package com.softuni.list.web;

import com.softuni.list.model.bind.UserLoginBindingModel;
import com.softuni.list.model.bind.UserRegisterBindingModel;
import com.softuni.list.model.service.UserServiceModel;
import com.softuni.list.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final Argon2PasswordEncoder argon2PasswordEncoder;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper, Argon2PasswordEncoder argon2PasswordEncoder) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.argon2PasswordEncoder = argon2PasswordEncoder;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession httpSession) {

        httpSession.invalidate();

        return super.redirect("/");
//
//        modelAndView.setViewName("redirect:/");
//        return modelAndView;

//        return "redirect:/";

    }

    @GetMapping("/login")
    public ModelAndView login(Model model,
                              HttpSession httpSession) {

        if (httpSession.getAttribute("user") != null) {
//            modelAndView.setViewName("redirect:/");
            return super.redirect("/");
//            return "redirect:/";
        }

        if (!model.containsAttribute("userLoginBindingModel")) {
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
            model.addAttribute("notFound", false);
        }


        //        modelAndView.setViewName("login");
        return super.view("login");
//        return new ModelAndView("login");
//        return "login";
    }

    @PostMapping("/login")
    public ModelAndView loginConfirm(@Valid
                                     @ModelAttribute(name = "userLoginBindingModel") UserLoginBindingModel userLoginBindingModel,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes,
                                     HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            redirectAttributes.addFlashAttribute("notFound", false);
//            modelAndView.addObject("userLoginBindingModel", userLoginBindingModel);
//            modelAndView.addObject("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
//            modelAndView.addObject("notFound", false);

            return super.redirect("login");

//            modelAndView.setViewName("redirect:login");
//            return modelAndView;
//            return "redirect:login";
        }

        UserServiceModel userServiceModel = this.userService.getUserByName(userLoginBindingModel.getUsername());
        if (userServiceModel == null || !this.argon2PasswordEncoder.matches(userLoginBindingModel.getPassword(), userServiceModel.getPassword())) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            redirectAttributes.addFlashAttribute("notFound", true);
//            modelAndView.addObject("userLoginBindingModel", userLoginBindingModel);
//            modelAndView.addObject("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
//            modelAndView.addObject("notFound", true);

            return super.redirect("login");
//            modelAndView.setViewName("redirect:login");
//            return modelAndView;
//            return "redirect:login";
        }

        httpSession.setAttribute("user", userLoginBindingModel);
        return super.redirect("/");
//        modelAndView.setViewName("redirect:/");
//        return modelAndView;
//        return "redirect:/";

    }

    @GetMapping("/register")
    public ModelAndView register(Model model,
                                 HttpSession httpSession) {

        if (httpSession.getAttribute("user") != null) {
            return super.redirect("/");
//            return new ModelAndView("redirect:/");
//            return "redirect:/";
        }

        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }

//        modelAndView.setViewName("register");
//        return new ModelAndView("register");
        return super.view("register");
//        return "register";
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@Valid
                                        @ModelAttribute(name = "userRegisterBindingModel") UserRegisterBindingModel userRegisterBindingModel,
                                        BindingResult bindingResult,
                                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
//            modelAndView.addObject("userRegisterBindingModel", userRegisterBindingModel);
//            modelAndView.addObject("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return super.redirect("register");
//            modelAndView.setViewName("register");
//            return modelAndView;
//            return "redirect:register";
        }

        UserServiceModel userServiceModel = this.userService.getUserByName(userRegisterBindingModel.getUsername());

        if (userServiceModel != null) {
//            modelAndView.addObject("userRegisterBindingModel", userRegisterBindingModel);
//            modelAndView.addObject("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            bindingResult.rejectValue("username", "error.userRegisterBindingModel", "Username is already taken");

//            modelAndView.setViewName("register");
//            return modelAndView;

            return super.redirect("register");

//            return "redirect:register";
        }

        userServiceModel = this.userService.getUserByEmail(userRegisterBindingModel.getEmail());

        if (userServiceModel != null) {
//            modelAndView.addObject("userRegisterBindingModel", userRegisterBindingModel);
//            modelAndView.addObject("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            bindingResult.rejectValue("email", "error.userRegisterBindingModel", "Email is already taken");


            return super.redirect("register");
//            modelAndView.setViewName("register");
//            return modelAndView;
//            return "redirect:register";
        }

        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
//            modelAndView.addObject("userRegisterBindingModel", userRegisterBindingModel);
//            modelAndView.addObject("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            bindingResult.rejectValue("confirmPassword", "error.userRegisterBindingModel", "Confirm password did not match");
//            modelAndView.setViewName("register");
//            return modelAndView;
//            return "redirect:register";
            return super.redirect("register");
        }

        this.userService.registerUser(this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class));

//        modelAndView.setViewName("redirect:login");
//        return modelAndView;
        return super.redirect("login");
//        return "redirect:login";
    }

}
