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
	private String FILEPATH;
	
	// private Constructor
	
	private AdminManager(String filepath) {
		this.allAdmins = new HashMap<String, Admin>();
		this.FILEPATH = filepath;
	}
	
	// Instance
	
	public static AdminManager getInstance(String filepath) {
		if (INSTANCE == null) {
			INSTANCE = new AdminManager(filepath);
		}
		return INSTANCE;
	}
	
	//Admin(String id, String name, String lastName, String jmbg, String adress, Gender gender, int wage,
	//	String username, String password)
	
	
	// Methods
	
	public void loadAdmins() throws NumberFormatException, IOException{
		File adminFile = new File(FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(adminFile));
		String line;
		while((line = reader.readLine()) != null) {
			String [] splitLine = line.split("\\|");
			int wage = Integer.parseInt(splitLine[0]);
			String password = splitLine[1];
			String username = splitLine[5];
			String name = splitLine[4];
			String id = splitLine[9];
			String jmbg = splitLine[3];
			String adress = splitLine[8];
			Gender gender = Gender.valueOf(splitLine[8]);
			String lastName = splitLine[7];
			
			Admin admin = new Admin(id, name, lastName, jmbg, adress, gender, wage, username, password);
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
	

	public void setAdmins(HashMap<String, Admin> admini) {
		this.allAdmins = admini;
		
	}

	
}
