package com.diplom.webinar.repository;


import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diplom.webinar.entity.User;
import com.diplom.webinar.entity.Webinar;

@Repository
public interface WebinarRepository extends JpaRepository<Webinar, Long>{
	List<Webinar> findByNameIgnoreCase(String name);
	List<Webinar> findByThemeIgnoreCase(String theme);
	List<Webinar> findByGoalIgnoreCase(String goal);
	
	List<Webinar> findByCreator(User creator);
	List<Webinar> findByDatesBetween(Date datesFrom, Date datesTo);
	
	List<Webinar> findByApproved(boolean aproved);
	List<Webinar> findByEnded(boolean ended);
}
