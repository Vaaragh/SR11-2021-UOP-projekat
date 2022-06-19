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
import models.Rental;
import tools.ToolKit;

public class RentalManager {
	
	private static RentalManager INSTANCE;
	private HashMap<String,Rental> allRentals;
	private static String FILEPATH = "text/rental.txt";

	
	// private Constructor
	
	private RentalManager() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.allRentals = new HashMap<String, Rental>();
	}
	
	// Instance
	
	public static RentalManager getInstance() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (INSTANCE == null) {
			INSTANCE = new RentalManager();
		}
		INSTANCE.loadRentals();
		return INSTANCE;
	}
	
	// Getters and Setters
	
	
	
	public HashMap<String, Rental> getAllRentals() {
		return allRentals;
	}
	
	public void setAllRentals(HashMap<String, Rental> allRentals) {
		this.allRentals = allRentals;
	}
	
	public static String getFILEPATH() {
		return FILEPATH;
	}

	public static void setFILEPATH(String fILEPATH) {
		FILEPATH = fILEPATH;
	}
	
	// Methods

	public void loadRentals() throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		File rentalFile = new File(FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(rentalFile));
		String line;
		while((line = reader.readLine()) != null) {
			Rental rental = new Rental();
			String [] splitLine = line.split("\\|");
			ToolKit.objectFromArray(splitLine, rental);
			this.allRentals.put(rental.getIdentification(), rental);
			
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
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		});
		writer.close();
		
		
	}
	
	public Rental findRental(String id){
		return this.allRentals.get(id);
	}
	
	// CRUD Operations
	
	public boolean createRental(String [] infoArray) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		Rental rental = new Rental();
		ToolKit.objectFromArray(infoArray, rental);
//		for (BookCopy bookCopy: rental.getBookList().values()) {
//			String bookCopyId = bookCopy.getIdentification();
//			BookCopyManager.getInstance().setAvailability(bookCopyId, false);
//		}
		if (!this.allRentals.keySet().contains(rental.getIdentification())) {
			this.allRentals.put(rental.getIdentification(), rental);
			this.reloadLists();
			return true;
		}
		return false;	
	}
	
	public boolean updateRental(String [] infoArray, String id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.allRentals.keySet().contains(id)) {			
			Rental rental = this.findRental(id);
			for (BookCopy bookCopy: rental.getBookList().values()) {
				String bookCopyId = bookCopy.getIdentification();
				BookCopyManager.getInstance().setAvailability(bookCopyId, true);
			}
			ToolKit.objectFromArray(infoArray, rental);
			for (BookCopy bookCopy: rental.getBookList().values()) {
				String bookCopyId = bookCopy.getIdentification();
				BookCopyManager.getInstance().setAvailability(bookCopyId, false);
			}			
			this.reloadLists();
			return true;
			
			
		}
		return false;
	}
	
	public boolean deleteRental(String id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.rentalStatusList(false).contains(this.allRentals.get(id))) {
			this.findRental(id).setDeleted(true);
			this.reloadLists();
			return true;
		}
		return false;
	}
	
	public boolean reverseDeleteRental(String id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.rentalStatusList(true).contains(this.allRentals.get(id))) {
			this.findRental(id).setDeleted(false);
			this.reloadLists();
			return true;
		}
		return false;
	}
	
	// List reloader and status checker
	
	public ArrayList<Rental> rentalStatusList(Boolean state){
		ArrayList<Rental> statusList = new ArrayList<Rental>();
		for (String rentalId: this.allRentals.keySet()) {
			if (this.allRentals.get(rentalId).isDeleted() == state) {
				if (!statusList.contains(this.allRentals.get(rentalId))) {
					statusList.add(this.allRentals.get(rentalId));
				}
			}
		}
		return statusList;
	}
	
	public void reloadLists() throws IOException {
		this.saveRentals();
	}
}
