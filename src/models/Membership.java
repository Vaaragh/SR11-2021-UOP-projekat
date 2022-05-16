package models;

public class Membership {
	
	protected String name;
	protected int price;
	protected boolean isDeleted;
	
	
	// Constructors
	
	public Membership() {
		this.isDeleted = false;
	}

	public Membership(String name, int price) {
		this.name = name;
		this.price = price;
		this.isDeleted = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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
		return "Membership [name=" + name + ", price=" + price + "]";
	}

	
	

}
