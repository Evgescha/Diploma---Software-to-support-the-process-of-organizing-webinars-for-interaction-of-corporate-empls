package com.diplom.webinar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diplom.webinar.entity.Platform;
import com.diplom.webinar.repository.PlatformRepository;

@Service
public class PlatformService extends CrudImpl<Platform>{
    
    public PlatformRepository repository;

	@Autowired
	public PlatformService(PlatformRepository repository) {
		super(repository);
		this.repository = repository;
	}
 
    public Platform findByName(String name) {
        return repository.findByNameIgnoreCase(name);
    }

}
