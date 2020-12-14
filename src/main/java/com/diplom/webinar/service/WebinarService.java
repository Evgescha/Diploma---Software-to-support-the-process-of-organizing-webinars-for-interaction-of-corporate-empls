package com.diplom.webinar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diplom.webinar.entity.Webinar;
import com.diplom.webinar.repository.WebinarRepository;

@Service
public class WebinarService extends CrudImpl<Webinar> {

	public WebinarRepository repository;

	@Autowired
	public WebinarService(WebinarRepository repository) {
		super(repository);
		this.repository = repository;
	}

//	List<Webinar> findByNameIgnoreCase(String name) {
//		return repository.findByNameIgnoreCase(name);
//	}
//
//	List<Webinar> findByThemeIgnoreCase(String theme) {
//		return repository.findByThemeIgnoreCase(theme);
//	}
//
//	List<Webinar> findByGoalIgnoreCase(String goal) {
//		return repository.findByGoalIgnoreCase(goal);
//	}
//
//	List<Webinar> findByCreator(User creator) {
//		return repository.findByCreator(creator);
//	}
//
//	List<Webinar> findByDateBetween(Date datesFrom, Date datesTo) {
//		return repository.findByDateBetween(datesFrom, datesTo);
//	}
//
//	List<Webinar> findByApproved(boolean aproved) {
//		return repository.findByApproved(aproved);
//	}
//
//	List<Webinar> findByEnded(boolean ended) {
//		return repository.findByEnded(ended);
//	}

}
