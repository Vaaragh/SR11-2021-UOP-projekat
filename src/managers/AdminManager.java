package managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import Enums.Gender;
import models.Admin;

public class AdminManager {
	
	private static AdminManager INSTANCE;
	private HashMap<String,Admin> allAdmis;
	
	// private Constructor
	
	private AdminManager() {
		this.allAdmis = new HashMap<String, Admin>();
	}
	
	// Instance
	
	public static AdminManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new AdminManager();
		}
		return INSTANCE;
	}
	
	//Admin(String id, String name, String lastName, String jmbg, String adress, Gender gender, int wage,
	//	String username, String password)

	
	// Methods
	
	public void loadAdmins() throws NumberFormatException, IOException{
		File adminFile = new File("text/admin.txt");
		BufferedReader reader = new BufferedReader(new FileReader(adminFile));
		String line;
		while((line = reader.readLine()) != null) {
			String [] splitLine = line.split("\\|");
			String id = splitLine[0];
			String name = splitLine[1];
			String lastName = splitLine[2];
			String jmbg = splitLine[3];
			String adress = splitLine[4];
			Gender gender = Gender.valueOf(splitLine[5]);
			int wage = Integer.parseInt(splitLine[6]);
			String username = splitLine[7];
			String password = splitLine[8];
			
			Admin admin = new Admin(id, name, lastName, jmbg, adress, gender, wage, username, password);
			this.allAdmis.put(id, admin);
		}
		reader.close();
	}
	
	public void saveAdmins() {
		
	}
	
	public Admin findAdmin(String id){
		return this.allAdmis.get(id);
	}
	
}
