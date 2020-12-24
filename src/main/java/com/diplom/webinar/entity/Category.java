package com.diplom.webinar.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Category extends AbstractEntity {
	String name;
    @OneToMany(fetch=FetchType.LAZY, mappedBy="category")
    private List<Webinar> webinares= new ArrayList<Webinar>();
    

    @ManyToMany(mappedBy = "categoriesToShow", fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<User>();

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
}
