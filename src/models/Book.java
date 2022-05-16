package models;

import Enums.Language;

public class Book {
	
	protected String id;
	protected String originalTitle;
	protected String author;
	protected String description;
	
	protected Genre genre;
	
	protected Language originalLanguage;
	
	protected int publishDate;
	
	protected boolean isDeleted;

	
	// Constructors
	
	public Book() {
		this.isDeleted = false;
	}


	public Book(String id, String originalTitle, String author, String description, Genre genre,
			Language originalLanguage, int publishDate) {
		this.id = id;
		this.originalTitle = originalTitle;
		this.author = author;
		this.description = description;
		this.genre = genre;
		this.originalLanguage = originalLanguage;
		this.publishDate = publishDate;
		this.isDeleted = false;
	}

	
	// Getters and Setters

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getOriginalTitle() {
		return originalTitle;
	}


	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Genre getGenre() {
		return genre;
	}


	public void setGenre(Genre genre) {
		this.genre = genre;
	}


	public Language getOriginalLanguage() {
		return originalLanguage;
	}


	public void setOriginalLanguage(Language originalLanguage) {
		this.originalLanguage = originalLanguage;
	}


	public int getPublishDate() {
		return publishDate;
	}


	public void setPublishDate(int publishDate) {
		this.publishDate = publishDate;
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
		return "Book [id=" + id + ", originalTitle=" + originalTitle + ", genre=" + genre + "]";
	}
	
	
	
	
	
	
	
}
	