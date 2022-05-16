package models;

import Enums.Gender;
import tools.ToolKit;

public class Main {

	public static void main(String[] args) {
		Member mem = new Member();
		mem.id = "12";
		mem.gender = Gender.MALE;
		mem.name = "p";
		mem.membershipType = new Membership("other", 200);
		System.out.println(mem);
		
		
		
		//Authorisation attempt
		String stri = "A123";
		System.out.println(stri.charAt(0));
		
		//ToolKit singleton visibility checks
		ToolKit tool = ToolKit.getTools();
		System.out.println(tool.getInfo2());

	}

}
