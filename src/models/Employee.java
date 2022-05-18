package models;

import Enums.Gender;

public abstract class Employee extends Person {
	
	protected int wage;
	protected String userName;
	protected String password;
	
	// Constructors
	
	public Employee() {
		super();
	}
	
	public Employee(String id, String name, String lastName, String jmbg, String adress, Gender gender, int wage, String username, String password, boolean isDeleted) {
		super(id, name, lastName, jmbg, adress, gender, isDeleted);
		this.wage = wage;
		this.userName = username;
		this.password = password;
	}
	
	// Getters and Setters

	public int getWage() {
		return wage;
	}

	public void setWage(int wage) {
		this.wage = wage;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public char authorityCheck() {
		return this.id.charAt(0);
	}
	
	
	
	
	
}
