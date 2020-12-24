package com.diplom.webinar.controller;

import java.security.Principal;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.diplom.webinar.entity.Category;
import com.diplom.webinar.entity.Platform;
import com.diplom.webinar.entity.User;
import com.diplom.webinar.entity.Webinar;
import com.diplom.webinar.exception.RecordNotFoundException;
import com.diplom.webinar.service.CategoryService;
import com.diplom.webinar.service.PlatformService;
import com.diplom.webinar.service.UserServiceImpl;
import com.diplom.webinar.service.WebinarService;

@Controller
@RequestMapping("/webinaries")
public class WebinarController {

	@Autowired
	WebinarService service;

	@Autowired
	UserServiceImpl serviceUser;

	@Autowired
	CategoryService serviceCategory;

	@Autowired
	PlatformService servicePlatform;

	@GetMapping
	@RequestMapping(path = { "/approved" })
	String getApprovedWebinars(Model model) {
		List<Webinar> list = service.repository.findAll();

		if (list == null)
			model.addAttribute("list", null);
		else {
			for (int i = list.size() - 1; i >= 0; i--) {
				if(!list.get(i).isApproved())
					list.remove(i);
			}
			model.addAttribute("list", list);
		}
		model.addAttribute("approved", true);
		return "webinaries-list";
	}

	@GetMapping
	@RequestMapping(path = { "/notapproved" })
	String getNotApprovedWebinars(Model model) {
		List<Webinar> list = service.repository.findAll();

		if (list == null)
			model.addAttribute("list", null);
		else{
			for (int i = list.size() - 1; i >= 0; i--) {
				if(list.get(i).isApproved())
					list.remove(i);
			}
			model.addAttribute("list", list);
		}
		model.addAttribute("approved", false);
		return "webinaries-list";
	}
	
	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String edit(Model model, @PathVariable(name = "id", required = false) Long id, Principal principal)
			throws RecordNotFoundException {

		List<Category> categories = serviceCategory.repository.findAll();
		List<Platform> platforms = servicePlatform.repository.findAll();
		List<User> users = serviceUser.repository.findAll();
		if (id != null) {
			Webinar entity = service.read(id);
			model.addAttribute("entity", entity);
		} else {
			model.addAttribute("entity", new Webinar());
		}
		model.addAttribute("categories", categories);
		model.addAttribute("platforms", platforms);
		model.addAttribute("users", users);
		return "webinaries-add-edit";
	}

	@RequestMapping(path = "/delete/{id}")
	public String delete(Model model, @PathVariable("id") Long id) throws Exception {
		service.delete(id);
		return "redirect:/webinaries/approved";
	}

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public String createOrUpdate(Webinar entity, @Param("categoryId") Long categoryId,
			@Param("platformId") Long platformId, @Param("creatorId") Long creatorId, @Param("timeStr") String timeStr, Principal principal)
			throws Exception {

		entity.setPlatform(servicePlatform.read(platformId));
		entity.setCategory(serviceCategory.read(categoryId));
		entity.setCreator(serviceUser.read(creatorId));
		try {
		entity.setTimes(Time.valueOf(timeStr + ":00"));
		}catch (Exception e) {
			entity.setTimes(Time.valueOf(timeStr));
		}
		service.create(entity);
		
		User findByUsername = serviceUser.findByUsername(principal.getName());
		for(String role:findByUsername.getRoleListNames())
			System.out.println(role);
		if(findByUsername!=null && findByUsername.getRoleListNames().contains("ROLE_USER"))
			return "redirect:/webinariesToUser";
		return "redirect:/webinaries/approved";
	}
	//список всех выбранных для похода
	@GetMapping
	@RequestMapping(path = { "/arhive" })
	public String getMyHistory(Model model) throws RecordNotFoundException {

		List<Webinar> list = service.repository.findAll();
		for (int i = list.size() - 1; i >= 0; i--) {
			if(!list.get(i).isEnded()) 
				list.remove(i);
		}

		model.addAttribute("isContains", true);
		model.addAttribute("isEnded", true);
		model.addAttribute("list", list);
		return "webinariesToUser";
	}
	
}
