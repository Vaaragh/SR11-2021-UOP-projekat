package managers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import models.Library;
import tools.ToolKit;


public class LibraryManager {
	

	private static LibraryManager INSTANCE;
	private HashMap<String,Library> allLibraries;
	private static String FILEPATH = "text/library.txt";
	
	// private Constructor
	
	private LibraryManager() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.allLibraries = new HashMap<String, Library>();
	}
	
	// Instance
	
	public static LibraryManager getInstance() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (INSTANCE == null) {
			INSTANCE = new LibraryManager();
		}
		INSTANCE.loadLibraries();
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

	public void loadLibraries() throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		File libraryFile = new File(FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(libraryFile));
		String line;
		while((line = reader.readLine()) != null) {
			Library library = new Library();
			String [] split = line.split("\\|");
			ToolKit.objectFromArray(split, library);
			this.allLibraries.put(library.getIdentification(), library);
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
	// CRUD Operations
	
	public boolean createLibrary(String [] infoArray) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		Library library = new Library();
		ToolKit.objectFromArray(infoArray, library);
		if (!this.allLibraries.keySet().contains(library.getIdentification())) {
			if (!this.alreadyExists(library)) {
				this.allLibraries.put(library.getIdentification(), library);
				this.reloadLists();
				return true;
			}
		}
		return false;	
	}
	
	public boolean updateLibrary(String [] infoArray, String id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.allLibraries.keySet().contains(id)) {
			Library tempLibrary = new Library();
			ToolKit.objectFromArray(infoArray, tempLibrary);
			if (!this.alreadyExists(tempLibrary)) {
				Library library = this.findLibrary(id);
				ToolKit.objectFromArray(infoArray, library);
				this.reloadLists();
				return true;
			}
			
		}
		return false;
	}
	
	public boolean deleteLibrary(String id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.libraryStatusList(true).keySet().contains(id)) {
			this.findLibrary(id).setDeleted(true);
			this.reloadLists();
			return true;
		}
		return false;
	}
	
	public boolean reverseDeleteLibrary(String id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.libraryStatusList(false).keySet().contains(id)) {
			this.findLibrary(id).setDeleted(false);
			this.reloadLists();
			return true;
		}
		return false;
	}
	
	// List reloader and status checker
	
	public HashMap<String,Library> libraryStatusList(Boolean state){
		HashMap<String,Library> statusList = new HashMap<String,Library>();
		for (String libraryId: this.allLibraries.keySet()) {
			if (this.allLibraries.get(libraryId).isDeleted() == state) {
				if (!statusList.keySet().contains(libraryId)) {
					statusList.put(libraryId,this.allLibraries.get(libraryId));
				}
			}
		}
		return statusList;
	}
	
	public void reloadLists() throws IOException {
		this.saveLibraries();
	}
	
	// Content collision checker	

	public boolean alreadyExists(Library library) {
		for (Library libraryE: this.allLibraries.values()) {
			if (libraryE.getName().equals(library.getName()) ||
				libraryE.getPhone().equals(library.getPhone())) {
				return true;
			}
		}
		return false;
	}	
}

