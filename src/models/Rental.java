package models;

import java.time.LocalDate;
import java.util.HashMap;

public class Rental {
	
	protected String id;
	
	protected LocalDate rentalDate;
	protected LocalDate dueDate;
	
	protected Employee employee;
	
	protected Member member;
	
	protected HashMap<String, BookCopy> bookList;
	protected boolean isDeleted;

	
	
	

	public Rental() {
	}

	public Rental(LocalDate rentalDate, LocalDate dueDate, Employee employee, Member member,
			HashMap<String, BookCopy> bookList,String id, boolean isDeleted) {
		this.rentalDate = rentalDate;
		this.dueDate = dueDate;
		this.employee = employee;
		this.member = member;
		this.bookList = bookList;
		this.isDeleted = isDeleted;
		this.id = id;
	}
	

	public LocalDate getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(LocalDate rentalDate) {
		this.rentalDate = rentalDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public HashMap<String, BookCopy> getBookList() {
		return bookList;
	}

	public void setBookList(HashMap<String, BookCopy> bookList) {
		this.bookList = bookList;
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

	@Override
	public String toString() {
		return "Rental [enployee=" + employee.id + ", member=" + member.id + ", bookList=" + bookList + "]";
	}
	
	
	
	
	

}
