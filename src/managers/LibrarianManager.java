package managers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import Enums.Gender;
import models.Librarian;
import tools.ToolKit;

public class LibrarianManager {

	private static LibrarianManager INSTANCE;
	private HashMap<String,Librarian> allLibrarians;
	private String FILEPATH;
	
	// private Constructor
	
	private LibrarianManager(String filepath) {
		this.allLibrarians = new HashMap<String, Librarian>();
		this.FILEPATH = filepath;
	}
	
	// Instance
	
	public static LibrarianManager getInstance(String filepath) {
		if (INSTANCE == null) {
			INSTANCE = new LibrarianManager(filepath);
		}
		return INSTANCE;
	}
	
	// Getters and Setters
	
	public HashMap<String, Librarian> getAllLibrarians() {
		return allLibrarians;
	}
	
	public void setAllLibrarians(HashMap<String, Librarian> allLibrarians) {
		this.allLibrarians = allLibrarians;
	}
	
	// Methods
	
	public void loadLibrarians() throws NumberFormatException, IOException{
		File librarianFile = new File(FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(librarianFile));
		String line;
		while((line = reader.readLine()) != null) {
			String [] splitLine = line.split("\\|");
			int wage = Integer.parseInt(splitLine[8]);
			String password = splitLine[6];
			String username = splitLine[7];
			String name = splitLine[5];
			String id = splitLine[2];
			String jmbg = splitLine[3];
			String adress = splitLine[0];
			Gender gender = Gender.valueOf(splitLine[1]);
			String lastName = splitLine[4];
			boolean deleted = Boolean.parseBoolean(splitLine[9]);
			
			Librarian librarian = new Librarian(id, name, lastName, jmbg, adress, gender, wage, username, password, deleted);
			this.allLibrarians.put(id, librarian);
		}
		reader.close();
	}
	
	public void saveLibrarians() throws IOException {
		File librarianFile = new File(FILEPATH);
		BufferedWriter writer = new BufferedWriter(new FileWriter(librarianFile));
		this.allLibrarians.forEach((key, value) -> {
			try {
				writer.write(ToolKit.generateFileLine(this.allLibrarians.get(key)));
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
	
	public Librarian findLibrarian(String id){
		return this.allLibrarians.get(id);
	}


	
}

