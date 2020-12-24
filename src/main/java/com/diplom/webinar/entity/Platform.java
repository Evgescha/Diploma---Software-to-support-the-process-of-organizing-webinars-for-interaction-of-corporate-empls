package com.diplom.webinar.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Platform extends AbstractEntity {
	@Column
	String name;
	@Column(length = 6000)
	String instruction;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="platform")
    private List<Webinar> webinares= new ArrayList<Webinar>();

	@Override
	public String toString() {
		return name;
	}
	
	
}
