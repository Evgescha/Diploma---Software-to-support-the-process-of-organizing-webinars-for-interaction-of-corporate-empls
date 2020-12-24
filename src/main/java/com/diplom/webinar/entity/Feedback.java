package com.diplom.webinar.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Feedback extends AbstractEntity {
	
	String author;
	
	@Column(length = 1024)
	String text;

	@ManyToOne
	@JoinColumn(name = "feedback_id")
	Webinar webinar;
    	
}
