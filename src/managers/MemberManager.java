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
import tools.ToolKit;

public class MemberManager {

	private static MemberManager INSTANCE;
	private HashMap<String,Member> allMembers;
	private static String FILEPATH = "text/member.txt";
	
	// private Constructor
	
	private MemberManager() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.allMembers = new HashMap<String, Member>();
	}
	
	// Instance
	
	public static MemberManager getInstance() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (INSTANCE == null) {
			INSTANCE = new MemberManager();
		}
		INSTANCE.loadMembers();
		INSTANCE.adjustMembershipActivity();
		return INSTANCE;
	}
	
	// Getters and Setters
	
	public HashMap<String, Member> getAllMembers() {
		return allMembers;
	}
	
	public void setAllMembers(HashMap<String, Member> allMembers) {
		this.allMembers = allMembers;
	}
	
	public static String getFILEPATH() {
		return FILEPATH;
	}

	public static void setFILEPATH(String fILEPATH) {
		FILEPATH = fILEPATH;
	}

	
	// Methods

	public void loadMembers() throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		File memberFile = new File(FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(memberFile));
		String line;
		while((line = reader.readLine()) != null) {
			Member member = new Member();
			String [] splitLine = line.split("\\|");
			ToolKit.objectFromArray(splitLine, member);
			this.allMembers.put(member.getIdentification(), member);
			
		}
		reader.close();
	}
	
	public void saveMembers() throws IOException {
		File memberFile = new File(FILEPATH);
		BufferedWriter writer = new BufferedWriter(new FileWriter(memberFile));
		this.allMembers.forEach((key, value) -> {
			try {
				writer.write(ToolKit.generateFileLine(this.allMembers.get(key)));
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
	
	public Member findMember(String id){
		return this.allMembers.get(id);
	}

	// CRUD Operations
	
	public boolean createMember(String [] infoArray) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		Member member = new Member();
		ToolKit.objectFromArray(infoArray, member);
		if (!this.allMembers.keySet().contains(member.getIdentification())) {
			if (!this.alreadyExists(member)) {
				this.allMembers.put(member.getIdentification(), member);
				this.reloadLists();
				return true;
			}
		}
		return false;	
	}
	
	public boolean updateMember(String [] infoArray, String id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.allMembers.keySet().contains(id)) {
			Member tempMember = new Member();
			ToolKit.objectFromArray(infoArray, tempMember);
			if (!this.alreadyExists(tempMember)) {
				Member member = this.findMember(id);
				ToolKit.objectFromArray(infoArray, member);
				this.reloadLists();
				return true;
			}
			
		}
		return false;
	}
	
	public boolean deleteMember(String id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.memberStatusList(true).keySet().contains(id)) {
			this.findMember(id).setDeleted(true);
			this.findMember(id).setActive(false);
			this.reloadLists();
			return true;
		}
		return false;
	}
	
	public boolean reverseDeleteMember(String id) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.memberStatusList(false).keySet().contains(id)) {
			this.findMember(id).setDeleted(false);
			this.reloadLists();
			return true;
		}
		return false;
	}
	
	// List reloader and status checker
	
	public HashMap<String,Member> memberStatusList(Boolean state){
		HashMap<String,Member> statusList = new HashMap<String,Member>();
		for (String memberId: this.allMembers.keySet()) {
			if (this.allMembers.get(memberId).isDeleted() == state) {
				if (!statusList.keySet().contains(memberId)) {
					statusList.put(memberId,this.allMembers.get(memberId));
				}
			}
		}
		return statusList;
	}
	
	public void reloadLists() throws IOException {
		this.saveMembers();
	}
	
	// Content collision checker	

	public boolean alreadyExists(Member member) {
		for (Member memberE: this.allMembers.values()) {
			if (memberE.getJmbg().equals(member.getJmbg()) ||
				memberE.getMembershipNumber().equals(member.getMembershipNumber())) {
				return true;
			}
		}
		return false;
	}	
	
	// Membership activity loader and adjuster
	
	public HashMap<String,Member> membershipStatusList(boolean state){
		HashMap<String,Member> activeMembers = this.memberStatusList(true);
		HashMap<String,Member> statusList = new HashMap<String,Member>();
		for (Member member: activeMembers.values()) {
			if (member.isActive()) {
				if (!statusList.keySet().contains(member.getIdentification())) {
					statusList.put(member.getIdentification(), member);
				}
			}
		}
		return statusList;
	}
	
	public void adjustMembershipActivity() {
		HashMap<String,Member> activeMembers = this.memberStatusList(true);
		for (Member member: activeMembers.values()) {
			member.setActive(ToolKit.evaluateTime(member.getLastPayment(), member.getMembershipLength()));
			
		}

	}
	
}
