package com.diplom.webinar.entity;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	
	
	
}
