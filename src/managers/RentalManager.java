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

import models.Admin;
import models.BookCopy;
import models.Employee;
import models.Member;
import models.Rental;
import tools.ToolKit;

public class RentalManager {
	
	private static RentalManager INSTANCE;
	private HashMap<String,Rental> allRentals;
	private static String FILEPATH = "text/rental.txt";

	
	// private Constructor
	
	private RentalManager() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.allRentals = new HashMap<String, Rental>();
		this.loadRentals();
	}
	
	// Instance
	
	public static RentalManager getInstance() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
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


	
}

