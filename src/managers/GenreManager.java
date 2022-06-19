package managers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import models.Book;
import models.Genre;
import tools.ToolKit;

public class GenreManager {
	
	private static GenreManager INSTANCE;
	private HashMap<String,Genre> allGenres;
	private static String FILEPATH = "text/genre.txt";
	
	// private Constructor
	
	private GenreManager() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.allGenres = new HashMap<String, Genre>();
	}
	
	// Instance
	
	public static GenreManager getInstance() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (INSTANCE == null) {
			INSTANCE = new GenreManager();
		}
		INSTANCE.loadGenres();
		return INSTANCE;
	}
	
	// Getters and Setters
	
	public HashMap<String, Genre> getAllGenres() {
		return allGenres;
	}
	
	public void setAllGenres(HashMap<String, Genre> allGenres) {
		this.allGenres = allGenres;
	}
	
	public static String getFILEPATH() {
		return FILEPATH;
	}

	public static void setFILEPATH(String fILEPATH) {
		FILEPATH = fILEPATH;
	}

	// Methods

	
	public void loadGenres() throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		File genreFile = new File(FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(genreFile));
		String line;
		while((line = reader.readLine()) != null) {
			Genre genre = new Genre();
			String [] splitLine = line.split("\\|");
			ToolKit.objectFromArray(splitLine, genre);
			this.allGenres.put(genre.getIdentification(), genre);
		}
		reader.close();
	}
	
	public void saveGenres() throws IOException {
		File genreFile = new File(FILEPATH);
		BufferedWriter writer = new BufferedWriter(new FileWriter(genreFile));
		this.allGenres.forEach((key, value) -> {
			try {
				writer.write(ToolKit.generateFileLine(this.allGenres.get(key)));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		});
		writer.close();
		
		
	}
	
	public Genre findGenre(String id){
		return this.allGenres.get(id);
	}
	
	// CRUD Operations
	
	public boolean createGenre(String [] infoArray) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		Genre genre = new Genre();
		ToolKit.objectFromArray(infoArray, genre);
		if (!this.allGenres.keySet().contains(genre.getIdentification())) {
			if (!this.alreadyExists(genre)) {
				this.allGenres.put(genre.getIdentification(), genre);
				this.reloadLists();
				return true;
			}
		}
		return false;	
	}
	
	public boolean updateGenre(String [] infoArray, String id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.allGenres.keySet().contains(id)) {
			Genre tempGenre = new Genre();
			ToolKit.objectFromArray(infoArray, tempGenre);
			if (!this.alreadyExists(tempGenre)) {
				Genre genre = this.findGenre(id);
				ToolKit.objectFromArray(infoArray, genre);
				this.reloadLists();
				return true;
			}
			
		}
		return false;
	}
	
	public boolean deleteGenre(String id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.genreStatusList(false).contains(this.allGenres.get(id))) {
			for (Book book: BookManager.getInstance().bookStatusList(true)) {
				if (book.getGenre().getIdentification().equals(id)) {
					return false;
				}
			}
			this.findGenre(id).setDeleted(true);
			this.reloadLists();
			return true;
		}
		return false;
	}
	
	public boolean reverseDeleteGenre(String id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.genreStatusList(true).contains(this.allGenres.get(id))) {
			this.findGenre(id).setDeleted(false);
			this.reloadLists();
			return true;
		}
		return false;
	}
	
	// List reloader and status checker
	
	public ArrayList<Genre> genreStatusList(Boolean state){
		ArrayList<Genre> statusList = new ArrayList<Genre>();
		for (String genreId: this.allGenres.keySet()) {
			if (this.allGenres.get(genreId).isDeleted() == state) {
				if (!statusList.contains(this.allGenres.get(genreId))) {
					statusList.add(this.allGenres.get(genreId));
				}
			}
		}
		return statusList;
	}
	
	public void reloadLists() throws IOException {
		this.saveGenres();
	}
	
	// Content collision checker	

	public boolean alreadyExists(Genre genre) {
		for (Genre genreE: this.allGenres.values()) {
			if (genreE.getIdentification().equals(genre.getIdentification())) {
				continue;
			}
			if (genreE.getGenreName().equals(genre.getGenreName()) ||
				genreE.getGenreDescription().equals(genre.getGenreDescription())) {
				return true;
			}
		}
		return false;
	}	
}
