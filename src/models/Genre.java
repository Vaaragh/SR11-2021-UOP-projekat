package models;

public class Genre {
	
	protected String identification;
	protected String genreName;
	protected String genreDescription;
	protected boolean isDeleted;
	
	// Constructors
	
	public Genre() {
		this.isDeleted = false;
	}
	
	public Genre(String id, String tag, String genreDescription, boolean isDeleted) {
		this.identification = id;
		this.genreName = tag;
		this.genreDescription = genreDescription;
		this.isDeleted = isDeleted;
	}
	
	// Getters and setters

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String id) {
		this.identification = id;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String tag) {
		this.genreName = tag;
	}

	public String getGenreDescription() {
		return genreDescription;
	}

	public void setGenreDescription(String genreDescription) {
		this.genreDescription = genreDescription;
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
		return "Genre [id=" + identification + ", tag=" + genreName + "]";
	}
	
	
	
	

	
	

}
