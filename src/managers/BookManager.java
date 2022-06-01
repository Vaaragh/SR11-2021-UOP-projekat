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
import models.Book;
import models.Genre;
import tools.ToolKit;

public class BookManager {
	
	private static BookManager INSTANCE;
	private HashMap<String,Book> allBooks;
	private static String FILEPATH = "text/book.txt";
	
	// private Constructor
	
	private BookManager() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.allBooks = new HashMap<String, Book>();
		this.loadBooks();

	}
	
	// Instance
	
	public static BookManager getInstance() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (INSTANCE == null) {
			INSTANCE = new BookManager();
		}
		return INSTANCE;
	}
	
	// Getters and Setters
	
	public HashMap<String, Book> getAllBooks() {
		return allBooks;
	}
	
	public void setAllBooks(HashMap<String, Book> allBooks) {
		this.allBooks = allBooks;
	}
	
	public static String getFILEPATH() {
		return FILEPATH;
	}

	public static void setFILEPATH(String fILEPATH) {
		FILEPATH = fILEPATH;
	}

	// Methods

	public void loadBooks() throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		File bookFile = new File(FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(bookFile));
		String line;
		while((line = reader.readLine()) != null) {
			Book book = new Book();
			String [] splitLine= line.split("\\|");			
			ToolKit.objectFromArray(splitLine, book);
			this.allBooks.put(book.getIdentification(), book);
			}
			reader.close();
	}
		
	
	public void saveBooks() throws IOException {
		File bookFile = new File(FILEPATH);
		BufferedWriter writer = new BufferedWriter(new FileWriter(bookFile));
		this.allBooks.forEach((key, value) -> {
			try {
				writer.write(ToolKit.generateFileLine(this.allBooks.get(key)));
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
	
	public Book findBook(String id){
		return this.allBooks.get(id);
	}	
}





