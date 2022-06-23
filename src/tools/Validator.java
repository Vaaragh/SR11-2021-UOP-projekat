package tools;

import java.util.regex.Pattern;

public class Validator {
	
	//LocalDate
	public static boolean isDateFormat(String string) {
		return Pattern.matches("^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$" 
			      + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
			      + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$" 
			      + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$", string);
	}
	
	//JMBG
	public static boolean isJmbgFormat(String string) {
		return Pattern.matches("([0-9]{13})", string);
	}
	
	//ID-MembershipNumber
	public static boolean isUUIDFormat(String string) {
		return Pattern.matches("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", string);
	}
	
	//Phone
	public static boolean isPhoneFormat(String string) {
		return Pattern.matches("([+](381)[6]{1}[0-9]{1}[0-9]{6}[0-9]{0,1})", string);
	}
	
	//Description
	public static boolean isTextFormat(String string) {
		return Pattern.matches("([A-Za-z0-9., &]*)", string);
	}
	
	//Address
	public static boolean isAddressFormat(String string) {
		return Pattern.matches("[A-Za-z0-9'\\\\.\\\\-\\\\s\\\\,\\\\ ]*", string);
	}
	
	//Name, Lastname, Title
	public static boolean isNameFormat(String string) {
		return Pattern.matches("(([A-Z]{1}([a-z]+)[ ]*[-]*)*)", string);
	}
	
	//Price, wage
	public static boolean isNumberFormat(String string) {
		return Pattern.matches("([1-9]+[0-9]*)", string);
	}
	
	//Print year, publish year
	public static boolean isYearFormat(String string) {
		return Pattern.matches("([1-9]{1}[0-9]{0,3})", string);
	}
	
	//Open, close
	public static boolean isTimeFormat(String string) {
		return Pattern.matches("(([0]{1}[0-9]{1}|[1]{1}[0-9]{1}|[2]{1}[0-3]{1})[:][0-5]{1}[0-9]{1})", string);
	}
	
	//Username, password
	public static boolean isNickFormat(String string) {
		return Pattern.matches("([A-Za-z0-9]{3,})", string);
	}
	
	//Temp carrier
	public static boolean isTempFormat(String string) {
		return true;
	}

}
