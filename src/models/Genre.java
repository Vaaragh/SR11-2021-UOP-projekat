package models;

public class Genre {
	
	protected String id;
	protected String tag;
	protected String genreDescription;
	protected boolean isDeleted;
	
	// Constructors
	
	public Genre() {
		this.isDeleted = false;
	}
	
	public Genre(String id, String tag, String genreDescription) {
		this.id = id;
		this.tag = tag;
		this.genreDescription = genreDescription;
		this.isDeleted = false;
	}
	
	// Getters and setters

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
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
		return "Genre [id=" + id + ", tag=" + tag + "]";
	}
	
	
	
	

	
	

}
