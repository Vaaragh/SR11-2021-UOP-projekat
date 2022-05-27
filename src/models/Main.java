package models;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import Enums.Binding;
import Enums.Gender;
import Enums.Language;
import managers.AdminManager;
import managers.BookCopyManager;
import managers.BookManager;
import managers.GenreManager;
import managers.LibrarianManager;
import managers.LibraryManager;
import managers.MemberManager;
import managers.MembershipManager;
import managers.RentalManager;
import tools.ToolKit;

public class Main {

	public static void main(String[] args) throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		
		// Files
		String ADMIN_FILE = "text/admin.txt";
		String LIBRARIAN_FILE = "text/librarian.txt";
		String GENRE_FILE = "text/genre.txt";
		String BOOK_FILE = "text/book.txt";
		String BOOK_COPY_FILE = "text/bookCopy.txt";
		String MEMBERSHIP_FILE = "text/membership.txt";
		String MEMBER_FILE = "text/member.txt";
		String RENTAL_FILE = "text/rental.txt";
		String LIBRARY_FILE = "text/library.txt";
		
		
		//Managers
		AdminManager adMan = AdminManager.getInstance();
		LibrarianManager liMan = LibrarianManager.getInstance();
		GenreManager genMan = GenreManager.getInstance();
		BookManager booMan = BookManager.getInstance();
		BookCopyManager booCoMan = BookCopyManager.getInstance();
		MembershipManager memShiMan = MembershipManager.getInstance();
		MemberManager memMan = MemberManager.getInstance();
		RentalManager renMan = RentalManager.getInstance();
		LibraryManager libMan = LibraryManager.getInstance();
		
		
		// Temporary HashMaps
		HashMap<String, Admin> admins = new HashMap<String, Admin>();
		HashMap<String, Librarian> librarians = new HashMap<String, Librarian>();
		HashMap<String, Genre> genres = new HashMap<String, Genre>();
		HashMap<String, Book> books = new HashMap<String, Book>();
		HashMap<String, BookCopy> bookCopies = new HashMap<String, BookCopy>();
		HashMap<String, Membership> memberships = new HashMap<String, Membership>();
		HashMap<String, Member> members = new HashMap<String, Member>();
		HashMap<String, Rental> rentals = new HashMap<String, Rental>();
		HashMap<String, Library> libraries = new HashMap<String, Library>();
		
		
		// Objects
		Library library = new Library("libraryId", "libraryName", "libraryAdress", "libraryPhone", LocalTime.of(8, 0, 0), LocalTime.of(16, 0, 0),false);
		Admin admin = new Admin("adminId", "adminName", "adminLasstName", "adminJmbg", "adminAdress", Gender.FEMALE, 200, "adminUsername", "adminPassword", false);
		Librarian librarian = new Librarian("librarianId", "librarianName", "librarianLastName", "librarianJmbg", "librarianAdress", Gender.OTHER, 100, "librarianUsername", "librarianPassword", false);
		Genre genre = new Genre("genreId", "genreTag", "genreDescription", false);
		Book book = new Book("bookId", "bookOgTitle", "bookAuthor", "bookDescription", genre, Language.ENGLISH, 1992, false);
		BookCopy bookCopy = new BookCopy("copyId","copyTitle", book,150,1999,Binding.HARDCOVER,Language.FRENCH, true, false);
		BookCopy bookCopy1 = new BookCopy("copyId2","copyTitle", book,150,1999,Binding.HARDCOVER,Language.FRENCH, true, false);
		Membership membership = new Membership("MembershipType", 200, "MembershipId",false);
		Member member = new Member("memberId","memberName","memberLastName", "memberJmbg", "memberAdress", Gender.FEMALE, "memberMembershipNum", LocalDate.now(), 3, membership, false);
		Rental rental = new Rental(LocalDate.now(), LocalDate.now().plusDays(14), librarian, member, bookCopies,"rentalId", false);
		
		
		
		
		// Linking Temporary to Managers
		libraries.put(library.getIdentification(), library);
		libMan.setAllLibraries(libraries);
		libMan.saveLibraries();
		libMan.loadLibraries();
		System.out.println(libMan.getAllLibraries());
		
//		admins.put(admin.getIdentification(), admin);
//		adMan.setAllAdmins(admins);		
//		adMan.saveAdmins();
//		adMan.loadAdmins();
//		System.out.println(adMan.getAllAdmins());
//		
//		librarians.put(librarian.getIdentification(), librarian);
//		liMan.setAllLibrarians(librarians);
//		liMan.saveLibrarians();
//		liMan.loadLibrarians();
//		System.out.println(liMan.getAllLibrarians());
//		
		genres.put(genre.getIdentification(), genre);
		genMan.setAllGenres(genres);
		genMan.saveGenres();
		genMan.loadGenres();
		System.out.println(genMan.getAllGenres());
		
		books.put(book.getIdentification(), book);
		booMan.setAllBooks(books);
		booMan.saveBooks();
		booMan.loadBooks();
		System.out.println(booMan.getAllBooks());
//		
//		bookCopies.put(bookCopy.getIdentification(), bookCopy);
//		bookCopies.put(bookCopy1.getIdentification(), bookCopy1);
//		booCoMan.setAllBookCopies(bookCopies);
//		booCoMan.saveBookCopies();
//		booCoMan.loadBookCopies();
//		System.out.println(booCoMan.getAllBookCopies());
//		
//		memberships.put(membership.getIdentification(), membership);
//		memShiMan.setAllMemberships(memberships);
//		memShiMan.saveMemberships();
//		memShiMan.loadMemberships();
//		System.out.println(memShiMan.getAllMemberships());
//		
//		members.put(member.getIdentification(), member);
//		memMan.setAllMembers(members);
//		memMan.saveMembers();
//		memMan.loadMembers();
//		System.out.println(memMan.getAllMembers());
//		
//		rentals.put(rental.getIdentification(), rental);
//		renMan.setAllRentals(rentals);
//		renMan.saveRentals();
//		renMan.loadRentals();
//		System.out.println(renMan.getAllRentals());
		
		
		
		// TEST BLOCK
		
		ArrayList<Object> allObjects = new ArrayList<Object>();
		allObjects.add(rental);
		allObjects.add(member);
		allObjects.add(admin);
		allObjects.add(librarian);
		allObjects.add(book);
		allObjects.add(bookCopy);
		allObjects.add(genre);
		allObjects.add(membership);
		allObjects.add(library);

		
		// Object Testing
		Object test = book;
		
		System.out.println(ToolKit.getAllFields(test.getClass()).size());
		HashMap<Field, Method> hashSet = ToolKit.getSetterHash(test.getClass(),ToolKit.getAllFields(test.getClass()));
		HashMap<Field, Method> hashGet = ToolKit.getGetterHash(test.getClass(),ToolKit.getAllFields(test.getClass()));
		
		for (Field f: hashSet.keySet()) {
			System.out.println(f.getGenericType() + "----"+ hashSet.get(f).getName());
		}
		System.out.println("------------");
		for (Field f: hashGet.keySet()) {
			System.out.println(f.getName() + "----"+ hashGet.get(f).getName());
		}	
		
		
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


