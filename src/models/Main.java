package models;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.HashMap;

import Enums.Binding;
import Enums.Gender;
import Enums.Language;
import managers.AdminManager;
import managers.BookCopyManager;
import managers.BookManager;
import managers.GenreManager;
import managers.LibrarianManager;

public class Main {

	public static void main(String[] args) throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		
		
		// Files
		String ADMIN_FILE = "text/admin.txt";
		String LIBRARIAN_FILE = "text/librarian.txt";
		String GENRE_FILE = "text/genre.txt";
		String BOOK_FILE = "text/book.txt";
		String BOOK_COPY_FILE = "text/bookCopy.txt";
		
		//Managers
		AdminManager adMan = AdminManager.getInstance(ADMIN_FILE);
		LibrarianManager libMan = LibrarianManager.getInstance(LIBRARIAN_FILE);
		GenreManager genMan = GenreManager.getInstance(GENRE_FILE);
		BookManager booMan = BookManager.getInstance(BOOK_FILE, GENRE_FILE);
		BookCopyManager booCoMan = BookCopyManager.getInstance(BOOK_COPY_FILE, BOOK_FILE, GENRE_FILE);
		
		
		
		
		// Objects
		Admin admin = new Admin("adminId", "adminName", "adminLasstName", "adminJmbg", "adminAdress", Gender.FEMALE, 200, "adminUsername", "adminPassword", false);
		Librarian librarian = new Librarian("librarianId", "librarianName", "librarianLastName", "librarianJmbg", "librarianAdress", Gender.OTHER, 100, "librarianUsername", "librarianPassword", false);
		Genre genre = new Genre("genreId", "genreTag", "genreDescription", false);
		Book book = new Book("bookId", "bookOgTitle", "bookAuthor", "bookDescription", genre, Language.ENGLISH, 1992, false);
		BookCopy bookCopy = new BookCopy("copyId","copyTitle", book,150,1999,Binding.HARDCOVER,Language.FRENCH, true, false);
		
		
		Membership membership = new Membership("MembershipType", 200, "MembershipId",false);
		Member mem = new Member("memberId","memberName","memberLastName", "memberJmbg", "memberAdress", Gender.FEMALE, "memberMembershipNum", LocalDate.now(), 3, membership, false);

		
		// Temporary HashMaps
		HashMap<String, Admin> admins = new HashMap<String, Admin>();
		HashMap<String, Librarian> librarians = new HashMap<String, Librarian>();
		HashMap<String, Genre> genres = new HashMap<String, Genre>();
		HashMap<String, Book> books = new HashMap<String, Book>();
		HashMap<String, BookCopy> bookCopies = new HashMap<String, BookCopy>();
		
		
		
		// Linking Temporary to Managers
		admins.put(admin.getId(), admin);
		adMan.setAllAdmins(admins);		
		adMan.loadAdmins();
		adMan.saveAdmins();
		
		librarians.put(librarian.getId(), librarian);
		libMan.setAllLibrarians(librarians);
		libMan.loadLibrarians();
		libMan.saveLibrarians();
		
		genres.put(genre.getId(), genre);
		genMan.setAllGenres(genres);
		genMan.loadGenres();
		genMan.saveGenres();
		genMan.loadGenres();

		
		books.put(book.getId(), book);
		booMan.setAllBooks(books);
		booMan.loadBooks();
		booMan.saveBooks();
		
		bookCopies.put(bookCopy.getId(), bookCopy);
		booCoMan.setAllBookCopies(bookCopies);
		booCoMan.loadBookCopies();
		booCoMan.saveBookCopies();
		
		
		
		
		

		
		
		
		//Authorisation check idea
//		String stri = "A123";
//		System.out.println(stri.charAt(0));
		
		
		
		// IllegalAccessException Generator
//		String s = ToolKit.generateFileLine(mem);
//		System.out.println(s);
//		System.out.println("-------------------------------");
//		ToolKit.generateFileLine(gen);
//		System.out.println("-------------------------------");
//		ToolKit.generateFileLine(boo);
	}

}
