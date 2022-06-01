package managers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import Enums.Gender;
import models.Admin;
import tools.ToolKit;

public class AdminManager {
	
	private static AdminManager INSTANCE;
	private HashMap<String,Admin> allAdmins;
	private static String FILEPATH = "text/admin.txt";
	
	// private Constructor
	
	private AdminManager() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.allAdmins = new HashMap<String, Admin>();
		this.loadAdmins();
	}
	
	// Instance
	
	public static AdminManager getInstance() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (INSTANCE == null) {
			INSTANCE = new AdminManager();
		}
		return INSTANCE;
	}
	
	// Getters and Setters
	
	public HashMap<String, Admin> getAllAdmins() {
		return allAdmins;
	}
	
	public void setAllAdmins(HashMap<String, Admin> allAdmins) {
		this.allAdmins = allAdmins;
	}
	
	public static String getFILEPATH() {
		return FILEPATH;
	}
	
	public static void setFILEPATH(String fILEPATH) {
		FILEPATH = fILEPATH;
	}
	
	// Methods
	
	public void loadAdmins() throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		File adminFile = new File(FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(adminFile));
		String line;
		while((line = reader.readLine()) != null) {
			Admin admin = new Admin();
			String [] splitLine = line.split("\\|");
			ToolKit.objectFromArray(splitLine, admin);
			this.allAdmins.put(admin.getIdentification(), admin);
			
		}
		reader.close();
	}
	
	
	
	public void saveAdmins() throws IOException {
		File adminFile = new File(FILEPATH);
		BufferedWriter writer = new BufferedWriter(new FileWriter(adminFile));
		this.allAdmins.forEach((key, value) -> {
			try {
				writer.write(ToolKit.generateFileLine(this.allAdmins.get(key)));
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
	
	public Admin findAdmin(String id){
		return this.allAdmins.get(id);
	}


	
}
