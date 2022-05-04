package models;

import java.time.LocalDate;

public class Member extends Person {
	
	protected String membershipNumber;
	protected LocalDate lastPayment;
	protected int membershipLength;
	

	protected Member() {
		super();
		this.membershipNumber = "";
		this.lastPayment = null;
		this.membershipLength = 0;
	}

	protected Member(String name, String lastName, String jmbg, String adress, boolean isActive, boolean isDeleted, String membershipNumber, LocalDate lastPayment, int membershipLenght) {
		super(name, lastName, jmbg, adress, isActive, isDeleted);
		this.membershipNumber = membershipNumber;
		this.lastPayment = lastPayment;
		this.membershipLength = membershipLenght;	
	}
	
	

}
