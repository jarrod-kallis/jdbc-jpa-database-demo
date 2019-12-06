package com.in28minutes.database.databasedemo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import com.in28minutes.database.databasedemo.jpa.PersonJpaRepository;

@Entity
// @Table(name="person")
@NamedQuery(name = PersonJpaRepository.QUERY_FIND_ALL, query = "select p from Person p")
public class Person {
	@Id
	@GeneratedValue
	private int id;

	// @Column(name="name")
	private String name;
	private String location;
	private Date dob;

	public Person() {
		super();
	}

	public Person(String name, String location, Date dob) {
		super();
		this.name = name;
		this.location = location;
		this.dob = dob;
	}

	public Person(int id, String name, String location, Date dob) {
		this(name, location, dob);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "\nPerson [id=" + id + ", name=" + name + ", location=" + location + ", dob=" + dob + "]";
	}
}
