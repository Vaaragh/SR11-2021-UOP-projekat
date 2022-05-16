package models;

import Enums.Gender;

public class Admin extends Employee {
	
	// Constructors

	public Admin() {
		super();
	}

	public Admin(String id, String name, String lastName, String jmbg, String adress, Gender gender) {
		super(id, name, lastName, jmbg, adress, gender);
	}


}
