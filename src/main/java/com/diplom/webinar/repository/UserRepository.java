package com.diplom.webinar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diplom.webinar.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

	User findByUsernameIgnoreCase(String username);
    
}