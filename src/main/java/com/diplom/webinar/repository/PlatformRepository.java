package com.diplom.webinar.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diplom.webinar.entity.Platform;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, Long>{
	Platform findByNameIgnoreCase(String name);  
}
