package com.diplom.webinar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diplom.webinar.entity.Feedback;
import com.diplom.webinar.entity.Webinar;
import com.diplom.webinar.repository.FeedbackRepository;
import com.diplom.webinar.repository.WebinarRepository;

@Service
public class WebinarService extends CrudImpl<Webinar> {

	public WebinarRepository repository;

	@Autowired
    FeedbackRepository repositoryFeedback;
    
	@Autowired
	public WebinarService(WebinarRepository repository) {
		super(repository);
		this.repository = repository;
	}

	@Override
	public void delete(long id) throws Exception {
		Webinar read = read(id);
		for(Feedback fb:read.getFeedbacks())
			repositoryFeedback.delete(fb);
		super.delete(id);
	}


}
