package managers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import models.Book;
import models.BookCopy;
import tools.ToolKit;

public class BookManager {
	
	private static BookManager INSTANCE;
	private HashMap<String,Book> allBooks;
	private static String FILEPATH = "text/book.txt";
	
	// private Constructor
	
	private BookManager() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.allBooks = new HashMap<String, Book>();
	}
	
	// Instance
	
	public static BookManager getInstance() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (INSTANCE == null) {
			INSTANCE = new BookManager();
		}
		INSTANCE.loadBooks();
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
	
	// CRUD Operations
	
	public boolean createBook(String [] infoArray) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		Book book = new Book();
		ToolKit.objectFromArray(infoArray, book);
		if (!this.allBooks.keySet().contains(book.getIdentification())) {
			if (!this.alreadyExists(book)) {
				this.allBooks.put(book.getIdentification(), book);
				this.reloadLists();
				return true;
			}
		}
		return false;	
	}
	
	public boolean updateBook(String [] infoArray, String id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.allBooks.keySet().contains(id)) {
			Book tempBook = new Book();
			ToolKit.objectFromArray(infoArray, tempBook);
			if (!this.alreadyExists(tempBook)) {
				Book book = this.findBook(id);
				ToolKit.objectFromArray(infoArray, book);
				this.reloadLists();
				return true;
			}
			
		}
		return false;
	}
	
	public boolean deleteBook(String id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.bookStatusList(true).keySet().contains(id)) {
			for (BookCopy bookCopy: BookCopyManager.getInstance().bookCopyStatusList(true).values()) {
				if (bookCopy.getBook().getIdentification().equals(id)) {
					return false;
				}
			}
			this.findBook(id).setDeleted(true);
			this.reloadLists();
			return true;
		}
		return false;
	}
	
	public boolean reverseDeleteBook(String id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.bookStatusList(false).keySet().contains(id)) {
			this.findBook(id).setDeleted(false);
			this.reloadLists();
			return true;
		}
		return false;
	}
	
	// List reloader and status checker
	
	public HashMap<String,Book> bookStatusList(Boolean state){
		HashMap<String,Book> statusList = new HashMap<String,Book>();
		for (String bookId: this.allBooks.keySet()) {
			if (this.allBooks.get(bookId).isDeleted() == state) {
				if (!statusList.keySet().contains(bookId)) {
					statusList.put(bookId,this.allBooks.get(bookId));
				}
			}
		}
		return statusList;
	}
	
	public void reloadLists() throws IOException {
		this.saveBooks();
	}
	
	// Content collision checker	

	public boolean alreadyExists(Book book) {
		for (Book bookE: this.allBooks.values()) {
			if (bookE.getDescription().equals(book.getDescription()) ||
				bookE.getOriginalTitle().equals(book.getOriginalTitle())) {
				return true;
			}
		}
		return false;
	}	
}






