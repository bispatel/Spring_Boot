package com.spring.demo.model;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Employee {

	// "customer_seq" is Oracle sequence name.
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMP_SEQ")
    @SequenceGenerator(sequenceName = "employee_seq", allocationSize = 1, name = "EMP_SEQ")
    Long id;

    String name;

	String email;

    @Column(name = "CREATED_DATE")
    Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}