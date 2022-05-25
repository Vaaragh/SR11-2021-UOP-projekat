package models;

import java.time.LocalTime;

public class Library {
	
	protected String identification;
	protected String name;
	protected String adress;
	protected String phone;
	
	protected LocalTime openFrom;
	protected LocalTime openUntill;
	
	protected boolean isDeleted;

	
	// Constructors
	
	public Library() {
		this.isDeleted = false;
	}



	public Library(String id, String name, String adress, String phone, LocalTime openFrom, LocalTime openUntill, boolean isDeleted) {
		this.identification = id;
		this.name = name;
		this.adress = adress;
		this.phone = phone;
		this.openFrom = openFrom;
		this.openUntill = openUntill;
		this.isDeleted = isDeleted;
	}

	
	// Getters and Setters

	public String getIdentification() {
		return identification;
	}



	public void setIdentification(String id) {
		this.identification = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getAdress() {
		return adress;
	}



	public void setAdress(String adress) {
		this.adress = adress;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public LocalTime getOpenFrom() {
		return openFrom;
	}



	public void setOpenFrom(LocalTime openFrom) {
		this.openFrom = openFrom;
	}



	public LocalTime getOpenUntill() {
		return openUntill;
	}



	public void setOpenUntill(LocalTime openUntill) {
		this.openUntill = openUntill;
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
		return "Library [name=" + name + ", adress=" + adress + ", openFrom=" + openFrom + ", openUntill=" + openUntill
				+ "]";
	}
	
	
	
	
	
	

}
