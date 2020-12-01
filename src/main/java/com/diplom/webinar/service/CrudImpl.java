package com.diplom.webinar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public class CrudImpl<Entity> implements CrudService<Entity> {

	public JpaRepository<Entity, Long> repository;

	@Override
	public boolean create(Entity entity) {
		try {
			repository.saveAndFlush(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Entity read(long id) {
		return repository.findById(id).isPresent() ? repository.findById(id).get() : null;
	}

	@Override
	public boolean update(Entity entity) {
		try {
			repository.saveAndFlush(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(long id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public CrudImpl(JpaRepository<Entity, Long> repository) {
		super();
		this.repository = repository;
	}

	public List<Entity> getAll() {
		List<Entity> result = repository.findAll();

		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<Entity>();
		}
	}
}
