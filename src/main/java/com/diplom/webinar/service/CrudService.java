package com.diplom.webinar.service;


public interface CrudService<Entity> {

    boolean create(Entity entity);

    Entity read(long id);

    boolean update(Entity entity);

    boolean delete(long id);
}