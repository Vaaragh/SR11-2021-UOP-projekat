package models;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.HashMap;

import Enums.Gender;
import Enums.Language;
import managers.AdminManager;
import tools.ToolKit;

public class Main {

	public static void main(String[] args) throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		
		String ADMIN_FILE = "text/admin.txt";
		
		AdminManager mana = AdminManager.getInstance(ADMIN_FILE);
		
		Membership membership = new Membership("MembershipCHILD", 200, "MembershipId");
		Member mem = new Member("memberId","memberName","memberLastName", "memberJmbg", "memberAdress", Gender.FEMALE, "memberMembershipNum", LocalDate.now(), 3, membership);
		Genre gen = new Genre("genreId", "genreTag", "genreDescription");
		Book boo = new Book("bookId", "bookOgTitle", "bookAuthor", "bookDescription", gen, Language.ENGLISH, 1992);
		Admin admin = new Admin("adminId", "adminName", "adminLasstName", "adminJmbg", "adminAdress", Gender.FEMALE, 200, "adminUsername", "adminPassword");

		
		
		HashMap<String, Admin> admini = new HashMap<String, Admin>();
		admini.put(admin.getId(), admin);
		mana.setAdmins(admini);
		
		mana.loadAdmins();
		mana.saveAdmins();
		ToolKit.generateFileLine(mem);
		
		
		
		//Authorisation check idea
//		String stri = "A123";
//		System.out.println(stri.charAt(0));
		
		
		
		// IllegalAccessException Generator
//		String s = ToolKit.generateFileLine(mem);
//		System.out.println(s);
//		System.out.println("-------------------------------");
//		ToolKit.generateFileLine(gen);
//		System.out.println("-------------------------------");
//		ToolKit.generateFileLine(boo);
		
		
		// writeLine
//		String st = "";
//		st += mem;
//		System.out.println(mem);
	}

}
