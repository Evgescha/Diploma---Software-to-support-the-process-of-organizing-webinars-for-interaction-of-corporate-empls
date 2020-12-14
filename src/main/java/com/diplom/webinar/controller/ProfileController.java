package com.diplom.webinar.controller;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
		return "profile";
	}
	
//	@RequestMapping(path = { "/edit", "/edit/{id}" })
//	public String edit(Model model, @PathVariable(name="id",required = false) Long id,Principal principal) throws RecordNotFoundException {
//		User user = userService.findByUsername(principal.getName());				
//		if (id!=null) {
//			Measurement entity = measurementService.read(id);
//			model.addAttribute("entity", entity);
//		} else {
//			model.addAttribute("entity", new Measurement());
//		}
//		return "profile-add-edit";
//	}
//
//	@RequestMapping(path = "/delete/{id}")
//	public String delete(Model model, @PathVariable("id") Long id,Principal principal) throws Exception {
//		User user = userService.findByUsername(principal.getName());
//		Measurement entity = measurementService.read(id);
//		user.getMeasurements().remove(entity);
//		measurementService.delete(id);
//		return "redirect:/profile";
//	}
//	
//	@RequestMapping(path = "/create", method = RequestMethod.POST)
//	public String createOrUpdate(Measurement entity,Principal principal) throws Exception {
//		User user = userService.findByUsername(principal.getName());	
//		entity.setUser(user);
//		measurementService.create(entity);
//		user.getMeasurements().add(entity);
//		userService.update(user);
//		return "redirect:/profile";
//	}
	
}
