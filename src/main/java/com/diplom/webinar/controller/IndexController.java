package com.diplom.webinar.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = { "/", "/index" })
public class IndexController {

	@GetMapping
	String getIndex() {
		return "index";
	}
	
}
