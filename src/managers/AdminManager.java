package managers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import Enums.Gender;
import models.Admin;
import tools.ToolKit;

public class AdminManager {
	
	private static AdminManager INSTANCE;
	private HashMap<String,Admin> allAdmins;
	private HashMap<String,Admin> activeAdmins;
	private HashMap<String,Admin> inactiveAdmins;
	private static String FILEPATH = "text/admin.txt";
	
	// private Constructor
	
	private AdminManager() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.allAdmins = new HashMap<String, Admin>();
		this.activeAdmins = new HashMap<String, Admin>();
		this.inactiveAdmins = new HashMap<String, Admin>();
		this.loadAdmins();
		this.activeAdmins = this.adminStatusList(true);
		this.inactiveAdmins =  this.adminStatusList(false);
	}
	
	// Instance
	
	public static AdminManager getInstance() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (INSTANCE == null) {
			INSTANCE = new AdminManager();
			return INSTANCE;
		}
		INSTANCE.loadAdmins();
		INSTANCE.activeAdmins =  INSTANCE.adminStatusList(true);
		INSTANCE.inactiveAdmins =  INSTANCE.adminStatusList(false);
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
	
	public HashMap<String, Admin> getActiveAdmins() {
		return activeAdmins;
	}
	
	public void setActiveAdmins(HashMap<String, Admin> activeAdmins) {
		this.activeAdmins = activeAdmins;
	}
	
	public HashMap<String, Admin> getInactiveAdmins() {
		return inactiveAdmins;
	}
	
	public void setInactiveAdmins(HashMap<String, Admin> inactiveAdmins) {
		this.inactiveAdmins = inactiveAdmins;
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
	
	// CRUD Operations
	
	public boolean createAdmin(String [] infoArray) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		Admin admin = new Admin();
		ToolKit.objectFromArray(infoArray, admin);
		if (!this.allAdmins.keySet().contains(admin.getIdentification())) {
			if (!this.alreadyExists(admin)) {
				this.allAdmins.put(admin.getIdentification(), admin);
				this.reloadLists();
				return true;
			}
		}
		return false;	
	}
	
	public boolean updateAdmin(String [] infoArray, String id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.allAdmins.keySet().contains(id)) {
			Admin tempAdmin = new Admin();
			ToolKit.objectFromArray(infoArray, tempAdmin);
			if (!this.alreadyExists(tempAdmin)) {
				Admin admin = this.findAdmin(id);
				ToolKit.objectFromArray(infoArray, admin);
				this.reloadLists();
				return true;
			}
			
		}
		return false;
	}
	
	public boolean deleteAdmin(String id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.activeAdmins.keySet().contains(id)) {
			this.findAdmin(id).setDeleted(true);
			this.reloadLists();
			return true;
		}
		return false;
	}
	
	public boolean reverseDeleteAdmin(String id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.inactiveAdmins.keySet().contains(id)) {
			this.findAdmin(id).setDeleted(false);
			this.reloadLists();
			return true;
		}
		return false;
	}
	
	// List reloader and status checker
	
	public HashMap<String,Admin> adminStatusList(Boolean state){
		HashMap<String,Admin> statusList = new HashMap<String,Admin>();
		for (String adminId: this.allAdmins.keySet()) {
			if (this.allAdmins.get(adminId).isDeleted() == state) {
				if (!statusList.keySet().contains(adminId)) {
					statusList.put(adminId,this.allAdmins.get(adminId));
				}
			}
		}
		return statusList;
	}
	
	public void reloadLists() throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		this.saveAdmins();
		this.loadAdmins();
		this.activeAdmins = this.adminStatusList(true);
		this.inactiveAdmins = this.adminStatusList(false);
	}
	
	// Content collision checker
	
	public boolean alreadyExists(Admin admin) {
		for (Admin adminE: this.allAdmins.values()) {
			if (adminE.getJmbg().equals(admin.getJmbg()) ||
				adminE.getUserName().equals(admin.getUserName())) {
				
			}
		}
		return true;
	}
	
	

	
}
