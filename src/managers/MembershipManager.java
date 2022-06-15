package managers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import models.Member;
import models.Membership;
import tools.ToolKit;

public class MembershipManager {
	
	private static MembershipManager INSTANCE;
	private HashMap<String,Membership> allMemberships;
	private static String FILEPATH = "text/membership.txt";
	
	// private Constructor
	
	private MembershipManager() throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		this.allMemberships = new HashMap<String, Membership>();
	}
	
	// Instance
	
	public static MembershipManager getInstance() throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (INSTANCE == null) {
			INSTANCE = new MembershipManager();
		}
		INSTANCE.loadMemberships();
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
	
	// CRUD Operations
	
	public boolean createMembership(String [] infoArray) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		Membership membership = new Membership();
		ToolKit.objectFromArray(infoArray, membership);
		if (!this.allMemberships.keySet().contains(membership.getIdentification())) {
			if (!this.alreadyExists(membership)) {
				this.allMemberships.put(membership.getIdentification(), membership);
				this.reloadLists();
				return true;
			}
		}
		return false;	
	}
	
	public boolean updateMembership(String [] infoArray, String id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.allMemberships.keySet().contains(id)) {
			Membership tempMembership = new Membership();
			ToolKit.objectFromArray(infoArray, tempMembership);
			if (!this.alreadyExists(tempMembership)) {
				Membership membership = this.findMembership(id);
				ToolKit.objectFromArray(infoArray, membership);
				this.reloadLists();
				return true;
			}
			
		}
		return false;
	}
	
	public boolean deleteMembership(String id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.membershipStatusList(true).keySet().contains(id)) {
			for (Member member: MemberManager.getInstance().memberStatusList(true).values()) {
				if (member.getMembershipType().getIdentification().equals(id)) {
					return false;
				}
			}
			this.findMembership(id).setDeleted(true);
			this.reloadLists();
			return true;
		}
		return false;
	}
	
	public boolean reverseDeleteMembership(String id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.membershipStatusList(false).keySet().contains(id)) {
			this.findMembership(id).setDeleted(false);
			this.reloadLists();
			return true;
		}
		return false;
	}
	
	// List reloader and status checker
	
	public HashMap<String,Membership> membershipStatusList(Boolean state){
		HashMap<String,Membership> statusList = new HashMap<String,Membership>();
		for (String membershipId: this.allMemberships.keySet()) {
			if (this.allMemberships.get(membershipId).isDeleted() == state) {
				if (!statusList.keySet().contains(membershipId)) {
					statusList.put(membershipId,this.allMemberships.get(membershipId));
				}
			}
		}
		return statusList;
	}
	
	public void reloadLists() throws IOException {
		this.saveMemberships();
	}
	
	// Content collision checker	

	public boolean alreadyExists(Membership membership) {
		for (Membership membershipE: this.allMemberships.values()) {
			if (membershipE.getName().equals(membership.getName())) {
				return true;
			}
		}
		return false;
	}	
}
