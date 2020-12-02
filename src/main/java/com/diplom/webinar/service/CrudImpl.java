package com.diplom.webinar.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public class CrudImpl<Entity> implements CrudService<Entity> {

    public JpaRepository<Entity, Long> repository;

    @Override
    public void create(Entity entity) throws Exception {
        repository.saveAndFlush(entity);
    }

    @Override
    public Entity read(long id) {
        return repository.findById(id).isPresent() ? repository.findById(id).get() : null;
    }

    @Override
    public void update(Entity entity) throws Exception {
        repository.saveAndFlush(entity);
    }

    @Override
    public void delete(long id) throws Exception {
        repository.deleteById(id);
    }

    public CrudImpl(JpaRepository<Entity, Long> repository) {
        super();
        this.repository = repository;
    }
    
    public List<Entity> getAll()
    {
        List<Entity> result = repository.findAll();
         
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Entity>();
        }
    }
}
