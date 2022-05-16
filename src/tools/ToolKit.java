package tools;

public class ToolKit {
	
	private static ToolKit INSTANCE;
	
	
	// Constructor
	
	private ToolKit() {
	}
	
	
	// Instance 
	
	public static ToolKit getTools() {
		if (INSTANCE == null) {
			INSTANCE = new ToolKit();
		}
		return INSTANCE;
	}
	
	
	// Methods
	
	public String getInfo2() {
		return "info2";
	}

}
