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
	private String FILEPATH = "text/admin.txt";
	
	// private Constructor
	
	private AdminManager() {
		this.allAdmins = new HashMap<String, Admin>();
	}
	
	// Instance
	
	public static AdminManager getInstance() {
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
	
	// Methods
	
	public void loadAdmins() throws NumberFormatException, IOException{
		File adminFile = new File(FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(adminFile));
		String line;
		while((line = reader.readLine()) != null) {
			String [] splitLine = line.split("\\|");
			int wage = Integer.parseInt(splitLine[8]);
			String password = splitLine[6];
			String username = splitLine[7];
			String name = splitLine[5];
			String id = splitLine[2];
			String jmbg = splitLine[3];
			String adress = splitLine[0];
			Gender gender = Gender.valueOf(splitLine[1]);
			String lastName = splitLine[4];
			boolean deleted = Boolean.parseBoolean(splitLine[9]);
			
			Admin admin = new Admin(id, name, lastName, jmbg, adress, gender, wage, username, password,deleted);
			this.allAdmins.put(id, admin);
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
			}
		});
		writer.close();
		
		
	}
	
	public Admin findAdmin(String id){
		return this.allAdmins.get(id);
	}


	
}
