package enums;

public enum RegexP {
	
	ID("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$"),
	NAME("([A-Z]{1}[a-z]+ *)+"),
	DATE("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))"),
	TEXT("([A-Z ,.-a-z0-9])*"),
	YEAR("([1-9]{1}[0-9]{3}"),
	NUMBER("([0-9]+[1-9]*)"),
	PHONE("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$"),
	TIME("^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$"),
	JMBG("[0-9]{13}"),
	TEMP("[A-Z a-z]+"),
	
	;
	
	//ID id
	//NAME firstName, lastName
	//DATE LocalDate
	//TEXT description, address
	//YEAR year
	//NUMBER price/wage
	//PHONE phone
	//TIME localTime
	//JMBG jmbg
	//TEMP testing
	
	public final String pattern;
	
	private RegexP(String pattern) {
		this.pattern = pattern;
	}
	
}
