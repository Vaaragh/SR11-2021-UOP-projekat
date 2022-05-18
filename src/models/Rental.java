package models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Rental {
	
	protected LocalDate rentalDate;
	protected LocalDate dueDate;
	
	protected Employee enployee;
	
	protected Member member;
	
	protected ArrayList<BookCopy> bookList;
	protected boolean isDeleted;

	
	
	
	public Rental() {
	}

	public Rental(LocalDate rentalDate, LocalDate dueDate, Employee enployee, Member member,
			ArrayList<BookCopy> bookList, boolean isDeleted) {
		this.rentalDate = rentalDate;
		this.dueDate = dueDate;
		this.enployee = enployee;
		this.member = member;
		this.bookList = bookList;
		this.isDeleted = isDeleted;
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

	public Employee getEnployee() {
		return enployee;
	}

	public void setEnployee(Employee enployee) {
		this.enployee = enployee;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public ArrayList<BookCopy> getBookList() {
		return bookList;
	}

	public void setBookList(ArrayList<BookCopy> bookList) {
		this.bookList = bookList;
	}
	
	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "Rental [enployee=" + enployee.id + ", member=" + member.id + ", bookList=" + bookList + "]";
	}
	
	
	
	
	

}
