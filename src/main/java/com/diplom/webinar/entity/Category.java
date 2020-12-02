package com.diplom.webinar.entity;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class Category extends AbstractEntity {
	String name;
}
