package com.diplom.webinar.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User extends AbstractEntity {

	@NotNull
	private String username;
	@NotNull
	private String password;
	@Transient
	private String passwordConfirm;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles;

	public List<String> getRoleListNames() {
		List<String> roleNames = new ArrayList<>();
		for (Role currRole : getRoles()) {
			roleNames.add(currRole.getName());
		}
		return roleNames;
	}

}