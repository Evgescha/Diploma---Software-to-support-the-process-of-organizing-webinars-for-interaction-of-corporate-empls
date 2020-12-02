package com.diplom.webinar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diplom.webinar.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    public User findByUsernameIgnoreCase(String username);  
    public User findByUsername(String username);
}
