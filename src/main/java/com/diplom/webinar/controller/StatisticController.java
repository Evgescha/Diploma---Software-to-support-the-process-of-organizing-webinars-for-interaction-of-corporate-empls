package com.diplom.webinar.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diplom.webinar.entity.Category;
import com.diplom.webinar.entity.Webinar;
import com.diplom.webinar.service.CategoryService;
import com.diplom.webinar.service.UserServiceImpl;
import com.diplom.webinar.service.WebinarService;

import lombok.Data;

@Controller
@RequestMapping("/statistic")
public class StatisticController {

	@Autowired
	CategoryService serviceCategory;
	
	@Autowired
	WebinarService serviceWebinar;
	
	@Autowired
	UserServiceImpl serviceUser;
	
	
	@GetMapping
	String getStatistic(Model model) {
		List<Category> categories1 = serviceCategory.repository.findAll();
		List<Webinar> webinaries= serviceWebinar.repository.findAll();
	
		List<CategMore> categories = new ArrayList<StatisticController.CategMore>(); 
		for(int i=0; i<categories1.size();i++) {
			CategMore tmp = new CategMore(categories1.get(i));
			int active=0;
			int all=tmp.getWebinares().size();
			int visited=0;
			for(Webinar web:tmp.getWebinares()) {
				//если утвержден и закончен, считаем количество участников что посетили
				if(web.isApproved() && web.isEnded())
					visited+=web.getUsers().size();
				// если утвержден и не закончен, считаем количество таких активных
				if(web.isApproved() && !web.isEnded())
					active++;
			}
			tmp.active=active;
			tmp.all=all;
			tmp.visited=visited;
			categories.add(tmp);			
		}
		
		
		model.addAttribute("categories", categories);
		model.addAttribute("webinaries", webinaries);
		return "statistic";
	}
	
	
	
	
	
	@Data
	class CategMore extends Category{
		int active;
		int all;
		int visited;
		
		public CategMore(Category ct) {
			this.setName(ct.getName());
			this.setId(ct.getId());
			this.setUsers(ct.getUsers());
			this.setWebinares(ct.getWebinares());
		}
	}
	
	
}
