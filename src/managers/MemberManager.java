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

import Enums.Gender;
import models.Member;
import models.Membership;
import tools.ToolKit;

public class MemberManager {

	
	private static MemberManager INSTANCE;
	private HashMap<String,Member> allMembers;
	private static String FILEPATH = "text/member.txt";
	private MembershipManager memShiMan;
	
	// private Constructor
	
	private MemberManager() {
		this.allMembers = new HashMap<String, Member>();
		this.memShiMan = MembershipManager.getInstance();
	}
	
	// Instance
	
	public static MemberManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MemberManager();
		}
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

	public void loadMembers() throws NumberFormatException, IOException{
		File adminFile = new File(FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(adminFile));
		String line;
		while((line = reader.readLine()) != null) {
			String [] splitLine = line.split("\\|");
			String adress = splitLine[0];
			Gender gender = Gender.valueOf(splitLine[1]);
			String id = splitLine[2];
			String jmbg = splitLine[3];
			String lastName = splitLine[4];
			LocalDate lastPayement = LocalDate.parse(splitLine[5]);
			int membershipLen = Integer.parseInt(splitLine[6]);
			String memNum = splitLine[7];
			Membership type = null;
			for (Membership m: memShiMan.getAllMemberships().values()) {
				if (m.getId().equals(splitLine[8])) {
					type = m;
				}
			}
			String name = splitLine[9];
			boolean deleted = Boolean.parseBoolean(splitLine[10]);
			
			Member member = new Member(id, name, lastName, jmbg, adress, gender, memNum, lastPayement, membershipLen, type, deleted);			
			this.allMembers.put(id, member);
		}
		reader.close();
	}
	
	public void saveMembers() throws IOException {
		File adminFile = new File(FILEPATH);
		BufferedWriter writer = new BufferedWriter(new FileWriter(adminFile));
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


	
}

	

