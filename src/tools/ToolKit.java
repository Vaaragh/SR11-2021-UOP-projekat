package tools;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ToolKit {
	// ---ATTEMPT AT AUTO GENERATING fileLine THROUGH FIELDS ---//
	// ---Had issues with IllegallAccessException ---//
//	public List<Field> getAllFields(Class clss){	
//		if (clss == null) {
//			return Collections.emptyList();
//		}
//		List<Field> result = new ArrayList<>(getAllFields(clss.getSuperclass()));
//		List<Field> filteredFields = Arrays.stream(clss.getDeclaredFields())
//				.filter(f -> Modifier.isPublic(f.getModifiers()) || Modifier.isProtected(f.getModifiers())|| Modifier.isPrivate(f.getModifiers()))
//				.collect(Collectors.toList());
//		result.addAll(filteredFields);
//		return result;	
//	}
	
	
	// Get a list of all methods and filter them to get all getters
	public static List<Method> getMethods(Class<? extends Object> clss){
		List<Method> methodList = Arrays.stream(clss.getMethods()).filter(f -> (f.getName().startsWith("g")||f.getName().startsWith("i")) && !(f.getName().equals("getClass"))).collect(Collectors.toList());
		return methodList;
	}
	
	// Get getID method for nested objects
	public static Method getIdMethod(Class<? extends Object> clss){
		List<Method> methodList = Arrays.stream(clss.getMethods()).filter(f -> (f.getName().equals("getId"))).collect(Collectors.toList());
		Method method = methodList.get(0);
		return method;
	}
	
	public static String generateFileLine(Object obj) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String fileFormat = "";
		List<Method> methods = getMethods(obj.getClass());
		Collections.sort(methods, new Comparator<Method>() {  // Sorted for consistent positioning

			// Override for comparing Methods (default doesn't implement Comparable)
			@Override
			public int compare(Method o1, Method o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		methods.forEach((el) -> System.out.println(el.getName().toString())); // Helper for coding positions
		for (int i = 0; i< methods.size(); i++) { // Generating specific segment for the fileFormat
			Method x = methods.get(i);
			Object s = x.invoke(obj); // calls the method on the object, getting the value for the field
			if (s.getClass().getPackageName().equalsIgnoreCase("models")) { // check for basic types vs custom written classes
				String id = getIdMethod(s.getClass()).invoke(s).toString(); // gets the getId method for specific object (no duck type?)
				fileFormat += id + "|";
			}else {
				fileFormat += s + "|";
			}
			
		}
		fileFormat += "\n";
		System.out.println(fileFormat);
		return fileFormat;
		
		
		// ---OLD ATTEMPT WITH FIELDS--- //
//		List<Field> f = this.getAllFields(obj.getClass());
//		System.out.println(f);
//		for (int i = 0; i < f.size(); i++) {
//			Field current = f.get(i);
//			Field cur;
//			System.out.println(current);
//			System.out.println(current.get(obj)); 
//			fileFormat += (String)(f.get(i).get(obj)) + "|";		
//		}
//		return fileFormat;
	}

	

}
