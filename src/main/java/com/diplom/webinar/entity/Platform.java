package com.diplom.webinar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class Platform extends AbstractEntity {
	@Column
	String name;
	@Column(length = 3000)
	String instruction;
}
