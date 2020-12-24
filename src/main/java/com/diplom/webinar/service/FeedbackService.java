package com.diplom.webinar.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diplom.webinar.entity.Feedback;
import com.diplom.webinar.repository.FeedbackRepository;

@Service
public class FeedbackService extends CrudImpl<Feedback>{
    
    public FeedbackRepository repository;

	@Autowired
	public FeedbackService(FeedbackRepository repository) {
		super(repository);
		this.repository = repository;
	}

}
