package models;

import java.time.LocalDate;

import Enums.Gender;

public class Member extends Person {
	
	protected String membershipNumber;
	protected LocalDate lastPayment;	
	protected int membershipLength;	
	protected Membership membershipType;

	// Constructors
	
	public Member() {
		super();
		
	}
	
	public Member(String id, String name, String lastName, String jmbg, String adress, Gender gender, String membershipNumber, LocalDate lastPayment, int membershipLength, Membership membershipType) {
		super(id, name, lastName, jmbg, adress, gender);
		this.membershipNumber = membershipNumber;
		this.lastPayment = lastPayment;
		this.membershipLength = membershipLength;
		this.membershipType = membershipType;
	}
	
	// Getters and Setters
	
	public String getMembershipNumber() {
		return membershipNumber;
	}
	public void setMembershipNumber(String membershipNumber) {
		this.membershipNumber = membershipNumber;
	}
	public LocalDate getLastPayment() {
		return lastPayment;
	}
	public void setLastPayment(LocalDate lastPayment) {
		this.lastPayment = lastPayment;
	}
	public int getMembershipLength() {
		return membershipLength;
	}
	public void setMembershipLength(int membershipLength) {
		this.membershipLength = membershipLength;
	}
	public Membership getMembershipType() {
		return membershipType;
	}
	public void setMembershipType(Membership membershipType) {
		this.membershipType = membershipType;
	}
	
	// Custom toString
	
	@Override
	public String toString() {
		return this.getClass() + "[membershipPrice=" + membershipType.price + ", id=" + id + ", name=" + name + "]";
	}
	
	
	
	
	
	
	

}
