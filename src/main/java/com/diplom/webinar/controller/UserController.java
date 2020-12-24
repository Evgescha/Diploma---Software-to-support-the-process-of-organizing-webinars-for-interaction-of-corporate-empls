package com.diplom.webinar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.diplom.webinar.entity.Platform;
import com.diplom.webinar.entity.User;
import com.diplom.webinar.exception.RecordNotFoundException;
import com.diplom.webinar.service.PlatformService;
import com.diplom.webinar.service.UserServiceImpl;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserServiceImpl service;

	@GetMapping
	@RequestMapping("/usersList")
	String getUserList(Model model) {
		List<User> list = service.repository.findAll();
		model.addAttribute("list", list);
		return "users-list";
	}
	
//	@RequestMapping(path = { "/edit", "/edit/{id}" })
//	public String edit(Model model, @PathVariable(name="id",required = false) Long id) throws RecordNotFoundException {
//		if (id!=null) {
//			User entity = service.read(id);
//			model.addAttribute("entity", entity);
//		} else {
//			model.addAttribute("entity", new User());
//		}
//		return "user-add-edit";
//	}

	@RequestMapping(path = "/delete/{id}")
	public String delete(Model model, @PathVariable("id") Long id) throws Exception {
		service.delete(id);
		return "redirect:/users/usersList";
	}

//	@RequestMapping(path = "/create", method = RequestMethod.POST)
//	public String createOrUpdate(User entity) throws Exception {
//		service.create(entity);
//		return "redirect:/";
//	}
}
