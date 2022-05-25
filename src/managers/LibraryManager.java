package managers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalTime;
import java.util.HashMap;

import models.Library;
import tools.ToolKit;


public class LibraryManager {
	

	private static LibraryManager INSTANCE;
	private HashMap<String,Library> allLibraries;
	private static String FILEPATH = "text/library.txt";
	
	// private Constructor
	
	private LibraryManager() {
		this.allLibraries = new HashMap<String, Library>();

	}
	
	// Instance
	
	public static LibraryManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new LibraryManager();
		}
		return INSTANCE;
	}
	
	// Getters and Setters
	
	
	public HashMap<String, Library> getAllLibraries() {
		return allLibraries;
	}
	
	public void setAllLibraries(HashMap<String, Library> allLibraries) {
		this.allLibraries = allLibraries;
	}
		
	public static String getFILEPATH() {
		return FILEPATH;
	}

	public static void setFILEPATH(String fILEPATH) {
		FILEPATH = fILEPATH;
	}

	
	// Methods

	public void loadLibraries() throws NumberFormatException, IOException{
		File libraryFile = new File(FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(libraryFile));
		String line;
		while((line = reader.readLine()) != null) {
			String [] splitLine = line.split("\\|");
			String adress = splitLine[0];
			String id = splitLine[1];
			String name = splitLine[2];
			LocalTime open = LocalTime.parse(splitLine[3]);
			LocalTime close = LocalTime.parse(splitLine[4]);
			String phone = splitLine[5];
			boolean deleted = Boolean.parseBoolean(splitLine[6]);
				
				Library library = new Library(id, name, adress, phone, open, close, deleted);
				this.allLibraries.put(id, library);
			}
			reader.close();
		}
	
	public void saveLibraries() throws IOException {
		File libraryFile = new File(FILEPATH);
		BufferedWriter writer = new BufferedWriter(new FileWriter(libraryFile));
		this.allLibraries.forEach((key, value) -> {
			try {
				writer.write(ToolKit.generateFileLine(this.allLibraries.get(key)));
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
	
	public Library findLibrary(String id){
		return this.allLibraries.get(id);
	}
}

