package com.example.demo;
import java.util.Random;
import java.lang.Math; 
public class Customer {
	private Long id;
	private String firstName, lastName;
	
	public Customer (Long id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		
	}
	public Customer () {
		setValuesToEmptyAndNewId();
		
	}
	
	public void setValuesToEmptyAndNewId() {
		Random rand = new Random();
		this.id = Math.abs(rand.nextLong());
		this.firstName = "";
		this.lastName = "";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
