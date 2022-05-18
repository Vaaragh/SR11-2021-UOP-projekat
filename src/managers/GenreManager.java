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
	private String FILEPATH;
	
	// private Constructor
	
	private GenreManager(String filepath) {
		this.allGenres = new HashMap<String, Genre>();
		this.FILEPATH = filepath;
	}
	
	// Instance
	
	public static GenreManager getInstance(String filepath) {
		if (INSTANCE == null) {
			INSTANCE = new GenreManager(filepath);
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
	
	// Methods
	
	public void loadGenres() throws NumberFormatException, IOException{
		File genreFile = new File(FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(genreFile));
		String line;
		while((line = reader.readLine()) != null) {
			String [] splitLine = line.split("\\|");
				String id = splitLine[1];
				String tag = splitLine[2];
				String genreDescription = splitLine[0];
				boolean deleted = Boolean.parseBoolean(splitLine[3]);
				
				Genre genre = new Genre(id, tag, genreDescription, deleted);
				this.allGenres.put(id, genre);
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
			}
		});
		writer.close();
		
		
	}
	
	public Genre findGenre(String id){
		return this.allGenres.get(id);
	}
}


