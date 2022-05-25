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
	private GenreManager genMan;
	
	// private Constructor
	
	private BookManager() {
		this.allBooks = new HashMap<String, Book>();
		this.genMan = GenreManager.getInstance();

	}
	
	// Instance
	
	public static BookManager getInstance() {
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

	public void loadBooks() throws NumberFormatException, IOException {

		File bookFile = new File(FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(bookFile));
		String line;
		while((line = reader.readLine()) != null) {
			String [] splitLine = line.split("\\|");			
				String author = splitLine[0];
				String description = splitLine[1];
				Genre genre = genMan.findGenre(splitLine[2]);
				String id = splitLine[3];
				Language language = Language.valueOf(splitLine[4]);
				String title = splitLine[5];
				int publishdate = Integer.parseInt(splitLine[6]);
				boolean deleted = Boolean.parseBoolean(splitLine[7]);
				
				
				Book book = new Book(id, title, author, description, genre, language, publishdate, deleted);
				this.allBooks.put(id, book);
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





