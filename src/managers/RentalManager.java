package managers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.HashMap;

import models.BookCopy;
import models.Employee;
import models.Member;
import models.Rental;
import tools.ToolKit;

public class RentalManager {
	
	private static RentalManager INSTANCE;
	private HashMap<String,Rental> allRentals;
	private String FILEPATH = "text/rental.txt";
	private BookCopyManager booCoMan;
	private AdminManager adMan;
	private LibrarianManager liMan;
	private MemberManager memMan;
	
	// private Constructor
	
	private RentalManager() {
		this.allRentals = new HashMap<String, Rental>();
		this.booCoMan = BookCopyManager.getInstance();
		this.adMan = AdminManager.getInstance();
		this.liMan = LibrarianManager.getInstance();
		this.memMan = MemberManager.getInstance();
	}
	
	// Instance
	
	public static RentalManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new RentalManager();
		}
		return INSTANCE;
	}
	
	// Getters and Setters
	
	public HashMap<String, Rental> getAllRentals() {
		return allRentals;
	}
	
	public void setAllRentals(HashMap<String, Rental> allRentals) {
		this.allRentals = allRentals;
	}
	
	// Methods
	
	public void loadRentals() throws NumberFormatException, IOException{
		File rentalFile = new File(FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(rentalFile));
		String line;
		while((line = reader.readLine()) != null) {
			String [] splitLine = line.split("\\|");
			HashMap <String, BookCopy> allCopies = new HashMap<String, BookCopy>();
			String [] allbooks = splitLine[0].split("\\;");
			for (String s: allbooks) {
				allCopies.put(s, booCoMan.findBookCopy(s));
			}
			LocalDate due = LocalDate.parse(splitLine[1]);
			Employee employee = liMan.findLibrarian(splitLine[2]);
			if (employee == null) {
				employee = adMan.findAdmin(splitLine[2]);
			}
			String id = splitLine[3];
			Member member = memMan.findMember(splitLine[4]);
			LocalDate rentDate = LocalDate.parse(splitLine[5]);
			boolean deleted = Boolean.parseBoolean(splitLine[6]);
			
			
			Rental rental = new Rental(rentDate, due, employee, member, allCopies, id, deleted);
			this.allRentals.put(id, rental);
		}
		reader.close();
	}
	
	public void saveRentals() throws IOException {
		File rentalFile = new File(FILEPATH);
		BufferedWriter writer = new BufferedWriter(new FileWriter(rentalFile));
		this.allRentals.forEach((key, value) -> {
			try {
				writer.write(ToolKit.generateFileLine(this.allRentals.get(key)));
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
	
	public Rental findRental(String id){
		return this.allRentals.get(id);
	}


	
}

