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
	
	
	// ---ATTEMPT AT AUTO GENERATING fileLine ---//\
	// === Missing all import === //
//	public List<Field> getAllFields(Class clss){	
//		if (clss == null) {
//			return Collections.emptyList();
//		}
//		List<Field> result = new ArrayList<>(getAllFields(clss.getSuperclass()));
//		List<Field> filteredFields = Arrays.stream(clss.getDeclaredFields())
//				.filter(f -> Modifier.isPublic(f.getModifiers()) || Modifier.isProtected(f.getModifiers()))
//				.collect(Collectors.toList());
//		result.addAll(filteredFields);
//		return result;	
//	}
//	
//	public String generateFileLine(Object obj) throws IllegalArgumentException, IllegalAccessException {
//		String fileFormat = "";
//		List<Field> f = this.getAllFields(obj.getClass());
//		for (int i = 0; i < f.size(); i++) {
//			Field current = f.get(i);
//			System.out.println(current);
////			System.out.println(current.get(obj)); 
////			fileFormat += (String)(f.get(i).get(obj)) + "|";		
//		}
//		return fileFormat;
//	}

	

}
