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

import models.BookCopy;
import tools.ToolKit;


public class BookCopyManager {
		
	private static BookCopyManager INSTANCE;
	private HashMap<String,BookCopy> allBookCopies;
	private static String FILEPATH = "text/bookCopy.txt";
	
	// private Constructor
	
	private BookCopyManager() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.allBookCopies = new HashMap<String, BookCopy>();
	}
	
	// Instance
	
	public static BookCopyManager getInstance() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (INSTANCE == null) {
			INSTANCE = new BookCopyManager();
		}
		INSTANCE.loadBookCopies();
		return INSTANCE;
	}
	
	// Getters and Setters
	
	public HashMap<String, BookCopy> getAllBookCopies() {
		return allBookCopies;
	}
	
	public void setAllBookCopies(HashMap<String, BookCopy> allBookCopies) {
		this.allBookCopies = allBookCopies;
	}
	
	public static String getFILEPATH() {
		return FILEPATH;
	}

	public static void setFILEPATH(String fILEPATH) {
		FILEPATH = fILEPATH;
	}

	// Methods
	
	public void loadBookCopies() throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		File bookCopyFile = new File(FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(bookCopyFile));
		String line;
		while((line = reader.readLine()) != null) {
			BookCopy bookCopy = new BookCopy();
			String [] splitLine = line.split("\\|");
			ToolKit.objectFromArray(splitLine, bookCopy);
			this.allBookCopies.put(bookCopy.getIdentification(), bookCopy);
			
		}
		reader.close();
	}
	
	
	public void saveBookCopies() throws IOException {
		File bookCopyFile = new File(FILEPATH);
		BufferedWriter writer = new BufferedWriter(new FileWriter(bookCopyFile));
		this.allBookCopies.forEach((key, value) -> {
			try {
				writer.write(ToolKit.generateFileLine(this.allBookCopies.get(key)));
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
	
	public BookCopy findBookCopy(String id){
		return this.allBookCopies.get(id);
	}
	
	// CRUD Operations

	public boolean createBookCopy(String [] infoArray) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		BookCopy bookCopy = new BookCopy();
		ToolKit.objectFromArray(infoArray, bookCopy);
		if (!this.allBookCopies.keySet().contains(bookCopy.getIdentification())) {
			if (!this.alreadyExists(bookCopy)) {
				this.allBookCopies.put(bookCopy.getIdentification(), bookCopy);
				this.reloadLists();
				return true;
			}
		}
		return false;	
	}
	
	public boolean updateBookCopy(String [] infoArray, String id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.allBookCopies.keySet().contains(id)) {
			BookCopy tempBookCopy = new BookCopy();
			ToolKit.objectFromArray(infoArray, tempBookCopy);
			if (!this.alreadyExists(tempBookCopy)) {
				BookCopy bookCopy = this.findBookCopy(id);
				ToolKit.objectFromArray(infoArray, bookCopy);
				this.reloadLists();
				return true;
			}
			
		}
		return false;
	}
	
	public boolean deleteBookCopy(String id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.bookCopyStatusList(false).contains(this.allBookCopies.get(id))) {
			if (this.findBookCopy(id).isAvailable()) {
				this.findBookCopy(id).setDeleted(true);
				this.reloadLists();
				return true;
			}
		}
		return false;
	}
	
	public boolean reverseDeleteBookCopy(String id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.bookCopyStatusList(true).contains(this.allBookCopies.get(id))) {
			this.findBookCopy(id).setDeleted(false);
			this.reloadLists();
			return true;
		}
		return false;
	}
	
	// List reloader and status checker
	
	public ArrayList<BookCopy> bookCopyStatusList(Boolean state){
		ArrayList<BookCopy> statusList = new ArrayList<BookCopy>();
		for (String bookCopyId: this.allBookCopies.keySet()) {
			if (this.allBookCopies.get(bookCopyId).isDeleted() == state) {
				if (!statusList.contains(this.allBookCopies.get(bookCopyId))) {
					statusList.add(this.allBookCopies.get(bookCopyId));
				}
			}
		}
		return statusList;
	}
	
	public void reloadLists() throws IOException {
		this.saveBookCopies();
	}
	
	// Content collision checker	

	public boolean alreadyExists(BookCopy bookCopy) {
		for (BookCopy bookCopyE: this.allBookCopies.values()) {
			if (bookCopyE.getIdentification().equals(bookCopy.getIdentification())) {
				continue;
			}
			if (bookCopyE.getTitle().equals(bookCopy.getTitle())) {
				return true;
			}
		}
		return false;
	}
	
	// Availability Setters
	
	public void setAvailability(String id, boolean available) throws IOException {
		this.findBookCopy(id).setAvailable(available);
		this.reloadLists();
	}
}
