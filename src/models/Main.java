package models;

import java.io.IOException;
import java.time.LocalDate;

import Enums.Gender;
import managers.AdminManager;
import tools.ToolKit;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		Membership membership = new Membership("CHILD", 200);
		Member mem = new Member("id","name","lastName", "jmbg", "adress", Gender.FEMALE, "memNum", LocalDate.now(), 3, membership);
		System.out.println(mem);
		ToolKit tool = ToolKit.getTools();
		AdminManager mana = AdminManager.getInstance();
		mana.loadAdmins();
		System.out.println(mana.findAdmin("1"));
		System.out.println(mana.findAdmin("2"));
		
		//Authorisation check idea
//		String stri = "A123";
//		System.out.println(stri.charAt(0));
		
		
		// writeLine
//		String st = "";
//		st += mem;
//		System.out.println(mem);
	}

}
