package managers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import models.Genre;
import tools.ToolKit;

public class GenreManager {

	
	private static GenreManager INSTANCE;
	private HashMap<String,Genre> allGenres;
	private static String FILEPATH = "text/genre.txt";
	
	// private Constructor
	
	private GenreManager() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.allGenres = new HashMap<String, Genre>();
		this.loadGenres();

	}
	
	// Instance
	
	public static GenreManager getInstance() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (INSTANCE == null) {
			INSTANCE = new GenreManager();
		}
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
}


