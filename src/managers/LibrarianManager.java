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
	}
	
	// Instance
	
	public static LibrarianManager getInstance() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (INSTANCE == null) {
			INSTANCE = new LibrarianManager();
		}
		INSTANCE.loadLibrarians();
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

	// CRUD Operations
	
	public boolean createLibrarian(String [] infoArray) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		Librarian librarian = new Librarian();
		ToolKit.objectFromArray(infoArray, librarian);
		if (!this.allLibrarians.keySet().contains(librarian.getIdentification())) {
			if (!this.alreadyExists(librarian)) {
				this.allLibrarians.put(librarian.getIdentification(), librarian);
				this.reloadLists();
				return true;
			}
		}
		return false;	
	}
	
	public boolean updateLibrarian(String [] infoArray, String id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.allLibrarians.keySet().contains(id)) {
			Librarian tempLibrarian = new Librarian();
			ToolKit.objectFromArray(infoArray, tempLibrarian);
			if (!this.alreadyExists(tempLibrarian)) {
				Librarian librarian = this.findLibrarian(id);
				ToolKit.objectFromArray(infoArray, librarian);
				this.reloadLists();
				return true;
			}
			
		}
		return false;
	}
	
	public boolean deleteLibrarian(String id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.librarianStatusList(false).contains(this.allLibrarians.get(id))) {
			this.findLibrarian(id).setDeleted(true);
			this.reloadLists();
			return true;
		}
		return false;
	}
	
	public boolean reverseDeleteLibrarian(String id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.librarianStatusList(true).contains(this.allLibrarians.get(id))) {
			this.findLibrarian(id).setDeleted(false);
			this.reloadLists();
			return true;
		}
		return false;
	}
	
	// List reloader and status checker
	
	public ArrayList<Librarian> librarianStatusList(Boolean state){
		ArrayList<Librarian> statusList = new ArrayList<Librarian>();
		for (String librarianId: this.allLibrarians.keySet()) {
			if (this.allLibrarians.get(librarianId).isDeleted() == state) {
				if (!statusList.contains(this.allLibrarians.get(librarianId))) {
					statusList.add(this.allLibrarians.get(librarianId));
				}
			}
		}
		return statusList;
	}
	
	public void reloadLists() throws IOException {
		this.saveLibrarians();
	}
	
	// Content collision checker

	public boolean alreadyExists(Librarian librarian) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		for (Librarian librarianE: this.allLibrarians.values()) {
			if (librarianE.getIdentification().equals(librarian.getIdentification())) {
				continue;
			}
			if (librarianE.getJmbg().equals(librarian.getJmbg()) ||
				librarianE.getUserName().equals(librarian.getUserName())) {
				return true;
			}
		}
		for (Admin adminE: AdminManager.getInstance().getAllAdmins().values()) {
			if (adminE.getJmbg().equals(librarian.getJmbg()) ||
				adminE.getUserName().equals(librarian.getUserName())) {
				return true;
			}
		}
		return false;
	}	
}

