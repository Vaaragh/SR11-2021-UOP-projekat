package managers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import Enums.Language;
import Enums.Binding;
import models.Admin;
import models.Book;
import models.BookCopy;
import tools.ToolKit;


public class BookCopyManager {
		
	private static BookCopyManager INSTANCE;
	private HashMap<String,BookCopy> allBookCopies;
	private static String FILEPATH = "text/bookCopy.txt";
	
	// private Constructor
	
	private BookCopyManager() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.allBookCopies = new HashMap<String, BookCopy>();
		this.loadBookCopies();

	}
	
	// Instance
	
	public static BookCopyManager getInstance() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (INSTANCE == null) {
			INSTANCE = new BookCopyManager();
		}
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
	
	
	
	
	
	
	
}
