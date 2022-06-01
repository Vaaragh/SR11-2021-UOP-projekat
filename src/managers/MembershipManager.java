package managers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import models.Admin;
import models.Membership;
import tools.ToolKit;

public class MembershipManager {
	
	private static MembershipManager INSTANCE;
	private HashMap<String,Membership> allMemberships;
	private static String FILEPATH = "text/membership.txt";
	
	// private Constructor
	
	private MembershipManager() throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		this.allMemberships = new HashMap<String, Membership>();
		this.loadMemberships();

	}
	
	// Instance
	
	public static MembershipManager getInstance() throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (INSTANCE == null) {
			INSTANCE = new MembershipManager();
		}
		return INSTANCE;
	}
	
	// Getters and Setters
	
	public HashMap<String, Membership> getAllMemberships() {
		return allMemberships;
	}
	
	public void setAllMemberships(HashMap<String, Membership> allMemberships) {
		this.allMemberships = allMemberships;
	}
		
	public static String getFILEPATH() {
		return FILEPATH;
	}

	public static void setFILEPATH(String fILEPATH) {
		FILEPATH = fILEPATH;
	}

	// Methods

	public void loadMemberships() throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		File membershipFile = new File(FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(membershipFile));
		String line;
		while((line = reader.readLine()) != null) {
			Membership membership = new Membership();
			String [] splitLine = line.split("\\|");
			ToolKit.objectFromArray(splitLine, membership);
			this.allMemberships.put(membership.getIdentification(), membership);
			
		}
		reader.close();
	}
	
	public void saveMemberships() throws IOException {
		File membershipFile = new File(FILEPATH);
		BufferedWriter writer = new BufferedWriter(new FileWriter(membershipFile));
		this.allMemberships.forEach((key, value) -> {
			try {
				writer.write(ToolKit.generateFileLine(this.allMemberships.get(key)));
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
	
	public Membership findMembership(String id){
		return this.allMemberships.get(id);
	}
}
