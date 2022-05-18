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
import models.Book;
import models.BookCopy;
import tools.ToolKit;


public class BookCopyManager {
		
	private static BookCopyManager INSTANCE;
	private HashMap<String,BookCopy> allBookCopies;
	private String FILEPATH;
	private BookManager booMan;
	
	// private Constructor
	
	private BookCopyManager(String filepath, String book, String genre) {
		this.allBookCopies = new HashMap<String, BookCopy>();
		this.FILEPATH = filepath;
		this.booMan = BookManager.getInstance(book, genre);

	}
	
	// Instance
	
	public static BookCopyManager getInstance(String filepath, String book, String genre) {
		if (INSTANCE == null) {
			INSTANCE = new BookCopyManager(filepath, book, genre);
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
	
	// Methods
	
	public void loadBookCopies() throws NumberFormatException, IOException{
		File bookCopyFile = new File(FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(bookCopyFile));
		String line;
		while((line = reader.readLine()) != null) {
			String [] splitLine = line.split("\\|");
			Binding binding = Binding.valueOf(splitLine[0]);
			Book book = null;
			for (Book b: booMan.getAllBooks().values()) {
				if (b.getId().equals(splitLine[1])) {
					book = b;
				}
			}
			String id = splitLine[2];
			int numPages = Integer.parseInt(splitLine[3]);
			int printDate = Integer.parseInt(splitLine[4]);
			Language language = Language.valueOf(splitLine[5]);
			String title = splitLine[6];
			boolean available = Boolean.parseBoolean(splitLine[7]);
			boolean deleted = Boolean.parseBoolean(splitLine[8]);
				
				
			BookCopy bookCopy = new BookCopy(id, title, book, numPages, printDate, binding, language, available, deleted);
			this.allBookCopies.put(id, bookCopy);
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
			}
		});
		writer.close();
		
		
	}
	
	public BookCopy findBookCopy(String id){
		return this.allBookCopies.get(id);
	}
}
