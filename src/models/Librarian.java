package models;

import Enums.Gender;

public class Librarian extends Employee {
	
	// Constructors

	public Librarian() {
		super();
	}

	public Librarian(String id, String name, String lastName, String jmbg, String adress, Gender gender) {
		super(id, name, lastName, jmbg, adress, gender);
	}
	
}
