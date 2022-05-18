package models;

import Enums.Gender;

public abstract class Person {
	
	protected String id;
	protected String name;
	protected String lastName;
	protected String jmbg;
	protected String adress;
	
	protected Gender gender;
	
	protected boolean isDeleted;

	// Constructors
	
	public Person() {
	this.isDeleted = false;
	}
	
	public Person(String id, String name, String lastName, String jmbg, String adress, Gender gender, boolean isDeleted) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.jmbg = jmbg;
		this.adress = adress;
		this.gender = gender;
		this.isDeleted = isDeleted;
	}

	// Getters and Setters
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	// toString
		
	@Override
	public String toString() {
		return this.getClass() + " [id=" + id + ", name=" + name + ", lastName=" + lastName + ", gender=" + gender + "]";
	}	
}
