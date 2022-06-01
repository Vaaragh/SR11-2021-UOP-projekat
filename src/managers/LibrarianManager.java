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
import models.Admin;
import models.Librarian;
import tools.ToolKit;

public class LibrarianManager {

	private static LibrarianManager INSTANCE;
	private HashMap<String,Librarian> allLibrarians;
	private static String FILEPATH = "text/librarian.txt";
	
	// private Constructor
	
	private LibrarianManager() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.allLibrarians = new HashMap<String, Librarian>();
		this.loadLibrarians();
	}
	
	// Instance
	
	public static LibrarianManager getInstance() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (INSTANCE == null) {
			INSTANCE = new LibrarianManager();
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
	
	public static String getFILEPATH() {
		return FILEPATH;
	}

	public static void setFILEPATH(String fILEPATH) {
		FILEPATH = fILEPATH;
	}
	

	// Methods


	public void loadLibrarians() throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		File librarianFile = new File(FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(librarianFile));
		String line;
		while((line = reader.readLine()) != null) {
			Librarian librarian = new Librarian();
			String [] splitLine = line.split("\\|");
			ToolKit.objectFromArray(splitLine, librarian);
			this.allLibrarians.put(librarian.getIdentification(), librarian);
			
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
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		});
		writer.close();
		
		
	}
	
	public Librarian findLibrarian(String id){
		return this.allLibrarians.get(id);
	}


	
}

