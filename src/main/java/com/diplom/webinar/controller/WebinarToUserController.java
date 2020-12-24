package com.diplom.webinar.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.diplom.webinar.entity.Category;
import com.diplom.webinar.entity.Feedback;
import com.diplom.webinar.entity.User;
import com.diplom.webinar.entity.Webinar;
import com.diplom.webinar.exception.RecordNotFoundException;
import com.diplom.webinar.service.CategoryService;
import com.diplom.webinar.service.FeedbackService;
import com.diplom.webinar.service.PlatformService;
import com.diplom.webinar.service.UserServiceImpl;
import com.diplom.webinar.service.WebinarService;

@Controller
@RequestMapping("/webinariesToUser")
public class WebinarToUserController {

	@Autowired
	WebinarService service;

	@Autowired
	UserServiceImpl serviceUser;

	@Autowired
	CategoryService serviceCategory;

	@Autowired
	PlatformService servicePlatform;
	
	@Autowired
	FeedbackService serviceFeedback;

	// список вебинаров кроме тех, на которые уже иду
	@GetMapping
	String getWebinaries(Model model, Principal principal) {

		User user = serviceUser.findByUsername(principal.getName());
		List<Webinar> list = service.repository.findAll();
		if (list == null)
			model.addAttribute("list", null);
		else {
			// цикл вебинаров с бд
			for (int i = list.size() - 1; i >= 0; i--) {
				// удаление не подтвержденных и тех, что уже прошли
				if (!list.get(i).isApproved() || list.get(i).isEnded()) {
					list.remove(i);
					continue;
				}
				
				if(list.size()==0)continue;
				
				// цикл вебинаров для посещения пользователей, удаляем те, на которые уже идем
				for (int j = 0; j < user.getWebinariesToGo().size(); j++) {

					if (user.getWebinariesToGo().get(j).getId() == list.get(i).getId()) {
						list.remove(i);
						continue;
					}
				}
				// проверяем, совпадает ли категория вебинаров с теми, что уже есть
				boolean isWebinarInRightCategory = false;
				for (Category cat : user.getCategoriesToShow()) {
					if (cat.getId() == list.get(i).getId())
						isWebinarInRightCategory = true;
				}
				if (!isWebinarInRightCategory)
					list.remove(i);
			}
			model.addAttribute("isContains", false);
			model.addAttribute("list", list);
		}

		return "webinariesToUser";
	}

	// когда согласился пойти на выбранный
	@GetMapping
	@RequestMapping(path = { "/igo/{id}" })
	public String addWebinarInList(Model model, @PathVariable("id") Long id, Principal principal) throws Exception {
		User user = serviceUser.findByUsername(principal.getName());
		user.getWebinariesToGo().add(service.read(id));
		serviceUser.update(user);

		return "redirect:/webinariesToUser/toGo";
	}

//список всех выбранных для похода
	@GetMapping
	@RequestMapping(path = { "/toGo" })
	public String getMyWebinatiesToGo(Model model, Principal principal) throws RecordNotFoundException {

		System.out.println("Webinar toGo");
		User user = serviceUser.findByUsername(principal.getName());

		List<Webinar> list = user.getWebinariesToGo();
		for (int i = list.size() - 1; i >= 0; i--) {
			if (!list.get(i).isApproved() || list.get(i).isEnded())
				list.remove(i);
		}

		model.addAttribute("isContains", true);
		model.addAttribute("list", list);
		return "webinariesToUser";
	}

	// список всех выбранных для похода
	@GetMapping
	@RequestMapping(path = { "/history" })
	public String getMyHistory(Model model, Principal principal) throws RecordNotFoundException {

		User user = serviceUser.findByUsername(principal.getName());
		List<Webinar> list = user.getWebinariesToGo();
		for (int i = list.size() - 1; i >= 0; i--) {
			if (!list.get(i).isEnded())
				list.remove(i);
		}

		model.addAttribute("isContains", true);
		model.addAttribute("isEnded", true);
		model.addAttribute("list", list);
		return "webinariesToUser";
	}

	// удаление выбранного из своего списка для похода
	@GetMapping
	@RequestMapping(path = "/delete/{id}")
	public String delete(Model model, @PathVariable("id") Long id, Principal principal) throws Exception {

		User user = serviceUser.findByUsername(principal.getName());
		for (int i = 0; i < user.getWebinariesToGo().size(); i++)
			if (user.getWebinariesToGo().get(i).getId() == id)
				user.getWebinariesToGo().remove(i);

		serviceUser.update(user);

		return "redirect:/webinariesToUser/toGo";
	}

	// получение информации о выбранном
	@GetMapping
	@RequestMapping(path = "/info/{id}")
	public String getInfo(Model model, @PathVariable("id") Long id, Principal principal) throws Exception {
		User user = serviceUser.findByUsername(principal.getName());

		Webinar read = service.read(id);
		boolean isContains = false;

		if (user == null)
			isContains = true;
		else
			for (int i = 0; i < user.getWebinariesToGo().size(); i++)
				if (user.getWebinariesToGo().get(i).getId() == id)
					isContains = true;
		model.addAttribute("isContains", isContains);
		if (read.isEnded())
			model.addAttribute("isEnded", true);
		model.addAttribute("entity", read);
		if(user!=null)
			model.addAttribute("username", user.getFio());
		else
			model.addAttribute("username", "admin");

		return "webinariesInfo";
	}
	
	
	// оставить отзыв
	@PostMapping
	@RequestMapping(path = "/addFeedback/{id}")
	public String addFeedback(Model model, @PathVariable("id") Long id, 
			@RequestParam(value = "text") String text,
			Principal principal) throws Exception {
		

		Webinar read = service.read(id);
		Feedback fb = new Feedback();
		fb.setAuthor(principal.getName());
		fb.setText(text);
		serviceFeedback.create(fb);
		Feedback feedback = serviceFeedback.repository.findByAuthorAndText(fb.getAuthor(), text);
		read.getFeedbacks().add(feedback);
		service.update(read);
		feedback.setWebinar(read);
		serviceFeedback.update(feedback);
		return "redirect:/webinariesToUser/info/"+id;
	}
}
