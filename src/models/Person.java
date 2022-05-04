package models;

public abstract class Person {
	
	protected String id;
	protected String name;
	protected String lastName;
	protected String jmbg;
	protected String adress;
	protected boolean isActive;
	protected boolean isDeleted;
	//protected Gender gender; + getter, setter
	
	
	public Person() {
		this.id = "";
		this.name = "";
		this.lastName = "";
		this.jmbg = "";
		this.adress = "";
		this.isActive = false;
		this.isDeleted = false;
	}
	
	public Person(String name, String lastName, String jmbg, String adress, boolean isActive, boolean isDeleted) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.jmbg = jmbg;
		this.adress = adress;
		this.isActive = isActive;
		this.isDeleted = isDeleted;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	
	

}
