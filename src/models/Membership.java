package models;

public class Membership {
	
	protected String id;
	protected String name;
	protected int price;
	protected boolean isDeleted;
	
	
	
	// Constructors
	
	public Membership() {
		this.isDeleted = false;
	}

	public Membership(String name, int price, String id, boolean isDeleted) {
		this.name = name;
		this.price = price;
		this.isDeleted = isDeleted;
		this.id = id;
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

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	// toString

	@Override
	public String toString() {
		return "Membership [name=" + name + ", price=" + price + "]";
	}

	
	

}
