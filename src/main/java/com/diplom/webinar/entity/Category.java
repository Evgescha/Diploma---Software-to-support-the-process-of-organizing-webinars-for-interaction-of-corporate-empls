package com.diplom.webinar.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Category extends AbstractEntity {
	String name;
    @OneToMany(fetch=FetchType.LAZY, mappedBy="category")
    private List<Webinar> webinares= new ArrayList<Webinar>();
	@Override
	public String toString() {
		return name;
	}
}
