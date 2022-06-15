package models;

import java.time.LocalDate;

import Enums.Gender;

public class Member extends Person {
	
	protected String membershipNumber;
	protected LocalDate lastPayment;	
	protected int membershipLength;	
	protected Membership membershipType;
	protected boolean isActive;

	// Constructors
	
	public Member() {
		super();
		
	}
	
	public Member(String id, String firstName, String surName, String jmbg, String adress, Gender gender, String membershipNumber, LocalDate lastPayment, int membershipLength, Membership membershipType, boolean isDeleted, boolean isActive) {
		super(id, firstName, surName, jmbg, adress, gender, isDeleted);
		this.membershipNumber = membershipNumber;
		this.lastPayment = lastPayment;
		this.membershipLength = membershipLength;
		this.membershipType = membershipType;
		this.isActive = isActive;
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
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	// Custom toString

	@Override
	public String toString() {
		return this.getClass() + "[membershipPrice=" + membershipType.price + ", id=" + identification + ", firstName=" + firstName + "]";
	}
	
	
	
	
	
	
	

}
