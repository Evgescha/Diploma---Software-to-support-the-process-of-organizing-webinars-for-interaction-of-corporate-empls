package com.diplom.webinar.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Webinar extends AbstractEntity {

	String name;
	String theme;
	String goal;

	String spiker;
	
	@ManyToOne
	@JoinColumn(name = "category_id")	
	Category category;

	
	Date dates;
	Time times;
	String annotaion;
	String dataToConnection;
	
	@ManyToOne
	@JoinColumn(name = "platform_id")
	Platform platform;

	@ManyToOne
	@JoinColumn(name = "creator_id")
	User creator;
	
	boolean approved=false;
	boolean ended=false;
	
	
	@ManyToMany(mappedBy = "webinariesToGo", fetch = FetchType.EAGER)
	private Collection<User> users = new ArrayList<User>();


	@OneToMany(fetch=FetchType.LAZY, mappedBy="webinar")
	private List<Feedback> feedbacks= new ArrayList<Feedback>();	
}
