package com.diplom.webinar.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.diplom.webinar.entity.Category;
import com.diplom.webinar.entity.User;
import com.diplom.webinar.exception.RecordNotFoundException;
import com.diplom.webinar.service.CategoryService;
import com.diplom.webinar.service.UserServiceImpl;
import com.diplom.webinar.service.WebinarService;

@Controller
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	UserServiceImpl userService;
	@Autowired
	WebinarService webinarService;
	@Autowired
	CategoryService categoryService;
	
	
	@GetMapping
	String getProfile(Model model,Principal principal) {
		User user = userService.findByUsername(principal.getName());
		
		model.addAttribute("entity", user);
		model.addAttribute("list", categoryService.repository.findAll());
		return "profile";
	}
	
	@RequestMapping("/edit")
	public String edit(Model model, 
			@RequestParam(value = "categ", required = true) int[] categ,
			Principal principal) throws Exception {
		User user = userService.findByUsername(principal.getName());
		
		if(categ!=null) {
			user.getCategoriesToShow().clear();
			for(int i=0; i<categ.length;i++)
			{
				user.getCategoriesToShow().add(categoryService.read(categ[i]));
			}
		}
		userService.update(user);
		model.addAttribute("success", "Выбранные категории сохранены");
		return "redirect:/profile";
	}

	
}
