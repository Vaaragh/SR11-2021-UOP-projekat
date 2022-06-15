package models;

import Enums.Gender;

public abstract class Person {
	
	protected String identification;
	protected String firstName;
	protected String familyName;
	protected String jmbg;
	protected String adress;
	
	protected Gender gender;
	
	protected boolean isDeleted;

	// Constructors
	
	public Person() {
	this.isDeleted = false;
	}
	
	public Person(String id, String firstName, String familyName, String jmbg, String adress, Gender gender, boolean isDeleted) {
		this.identification = id;
		this.firstName = firstName;
		this.familyName = familyName;
		this.jmbg = jmbg;
		this.adress = adress;
		this.gender = gender;
		this.isDeleted = isDeleted;
	}

	// Getters and Setters
	
	public String getIdentification() {
		return identification;
	}

	public void setIdidentification(String id) {
		this.identification = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
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
		return this.getClass() + " [id=" + identification + ", firstName=" + firstName + ", familyName=" + familyName + ", gender=" + gender + "]";
	}	
}
