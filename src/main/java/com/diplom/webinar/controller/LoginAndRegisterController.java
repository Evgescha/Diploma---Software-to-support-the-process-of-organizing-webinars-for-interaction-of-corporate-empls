package com.diplom.webinar.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.diplom.webinar.entity.User;
import com.diplom.webinar.service.UserServiceImpl;

@Controller
@RequestMapping
public class LoginAndRegisterController {

    @Autowired
    UserServiceImpl userService;

    
    @PostMapping("/registration")
    public String registraionUser(Model model, @ModelAttribute User user,RedirectAttributes redirectAttributes) {
        boolean success = userService.userRegistration(user);
        String response = success ? "Успешно зарегистрирован" : "Ошибка регистрации. Попробуйте позже.";
        redirectAttributes.addFlashAttribute("success", response);
        return "redirect:/";
    }
    @GetMapping("/registration")
    public String getRegistrationPage() {
    	return "registration";
    }
    

}
