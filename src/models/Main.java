package models;

import Enums.Gender;

public class Main {

	public static void main(String[] args) {
		Admin admin = new Admin("123", "Pera", "Peric", "12345", "a", Gender.MALE);
		Librarian lib = new Librarian("123", "ime", "prezime", "1234", "adresa", Gender.FEMALE);
		
		System.out.println(admin);

		System.out.println(lib);
		
	

	}

}
