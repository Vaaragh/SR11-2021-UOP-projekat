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
import models.Admin;
import models.Member;
import models.Membership;
import tools.ToolKit;

public class MemberManager {

	
	private static MemberManager INSTANCE;
	private HashMap<String,Member> allMembers;
	private static String FILEPATH = "text/member.txt";
	
	// private Constructor
	
	private MemberManager() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.allMembers = new HashMap<String, Member>();
		this.loadMembers();
	}
	
	// Instance
	
	public static MemberManager getInstance() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
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


	
}

	

