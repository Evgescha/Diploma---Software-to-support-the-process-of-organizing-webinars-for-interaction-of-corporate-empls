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
import com.diplom.webinar.exception.RecordNotFoundException;
import com.diplom.webinar.service.PlatformService;

@Controller
@RequestMapping("/platforms")
public class PlatformsController {

	@Autowired
	PlatformService service;

	@GetMapping
	String getPlatform(Model model) {
		List<Platform> list = service.repository.findAll();
		if (list == null)
			model.addAttribute("list", null);
		else
			model.addAttribute("list", list);
		return "platforms-list";
	}
	
	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String edit(Model model, @PathVariable(name="id",required = false) Long id) throws RecordNotFoundException {
		if (id!=null) {
			Platform entity = service.read(id);
			model.addAttribute("entity", entity);
		} else {
			model.addAttribute("entity", new Platform());
		}
		return "platforms-add-edit";
	}

	@RequestMapping(path = "/delete/{id}")
	public String delete(Model model, @PathVariable("id") Long id) throws Exception {
		service.delete(id);
		return "redirect:/platforms";
	}

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public String createOrUpdate(Platform entity) throws Exception {
		service.create(entity);
		return "redirect:/platforms";
	}
}
