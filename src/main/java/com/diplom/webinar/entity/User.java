package com.diplom.webinar.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Table(name = "myUsers")
@Data
public class User extends AbstractEntity {

    @NotNull
    private String username;
    
    @JsonIgnore
    @NotNull
    private String password;
    
    @NotNull
    private String fio;
    
    @NotNull
    private Date dateBorn;
   
    public User() {
    	super();
    }
    

    @OneToMany(fetch=FetchType.LAZY, mappedBy="creator")
    private List<Webinar> createdWebinares= new ArrayList<Webinar>();
    
    @JsonIgnore
    @Fetch(value = FetchMode.SELECT)
    @ManyToMany(cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"),
        uniqueConstraints = @UniqueConstraint(
                name="users_roles",
                columnNames = {"user_id", "role_id"})
    )
    private Collection<Role> roles = new ArrayList<Role>();
   
    
    @JsonIgnore
    @Fetch(value = FetchMode.SELECT)
    @ManyToMany(cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinTable(name = "user_webinar",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "webinar_id"),
        uniqueConstraints = @UniqueConstraint(
                name="users_webinaries",
                columnNames = {"user_id", "webinar_id"})
    )
    private List<Webinar> webinariesToGo = new ArrayList<Webinar>();
   
    
    
    
    public List<String> getRoleListNames() {
        List<String> roleNames = new ArrayList<>();
        for (Role currRole : getRoles()) {
            roleNames.add(currRole.getName());
        }
        return roleNames;
    }

	@Override
	public String toString() {
		return username;
	}
    
    

}
