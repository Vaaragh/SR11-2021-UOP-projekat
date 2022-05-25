package tools;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ToolKit {
	
	// Get a list of all methods and filter them to get all getters
	public static List<Method> getGetters(Class<? extends Object> clss){
		List<Method> methodList = Arrays.stream(clss.getMethods()).filter(f -> (f.getName().startsWith("get")||f.getName().startsWith("is")) && !(f.getName().equals("getClass"))).collect(Collectors.toList());
		return methodList;
	}
	
	// Get getID method for nested objects
	public static Method getIdMethod(Class<? extends Object> clss) throws NoSuchMethodException, SecurityException{
		Method method = clss.getMethod("getId", (Class<?>[])null);
		return method;
	}
	
	// generate fileLine for writing into text for specific object
	public static String generateFileLine(Object obj) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String fileFormat = "";
		List<Method> methods = getGetters(obj.getClass());
		Collections.sort(methods, new Comparator<Method>() {  // Sorted for consistent positioning

			// Override for comparing Methods (default doesn't implement Comparable)
			@Override
			public int compare(Method o1, Method o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
//		methods.forEach((el) -> System.out.println(el.getName().toString())); // Helper for coding positions
		for (int i = 0; i< methods.size(); i++) { // Generating specific segment for the fileFormat
			Method method = methods.get(i);
			Object value = method.invoke(obj); // calls the method on the object, getting the value for the field
			if (value.getClass().getPackageName().equalsIgnoreCase("models")) { // check for basic types vs custom types (Objects)
				String id = getIdMethod(value.getClass()).invoke(value).toString(); // gets the getId method for specific object (no duck type?)
				fileFormat += id + "|";
			}else if(value instanceof HashMap){
				String allelements = "";
				Set<?> set = ((HashMap<?, ?>)value).keySet();
				for (Object s1: set) {
					allelements += s1 + ";";
				}
				fileFormat += allelements + "|";
			}else {
				fileFormat += value + "|";
				
			}
			
		}
		fileFormat += "\n";
		System.out.println(fileFormat);
		return fileFormat;
	}
	
	
	
	
	
	// Test Block
	
	// Get a list of all methods and filter them to get all setters
	public static List<Method> getSetters(Class<? extends Object> clss) {
		List<Method> methodList = Arrays.stream(clss.getMethods()).filter(f -> (f.getName().startsWith("set"))).collect(Collectors.toList());
		return methodList;
	}
	// Get a list of all declared fields of a class and the superclass, for alignment of methods
	public static List<Field> getAllFields(Class<? extends Object> clss){	
		if (clss == null) {
			return Collections.emptyList();
		}
		List<Field> result = new ArrayList<>(getAllFields(clss.getSuperclass()));
		List<Field> filteredFields = Arrays.stream(clss.getDeclaredFields())
				.filter(f -> Modifier.isPublic(f.getModifiers()) || Modifier.isProtected(f.getModifiers()))
				.collect(Collectors.toList());
		result.addAll(filteredFields);
		List<String> returnList = new ArrayList<String>();
		result.forEach((f) -> returnList.add(f.getName().toLowerCase()));
		return result;	
	}
	
	// Turn the fields into Strings, for sorting and alignment of methods
	public static List<String> getAllFieldStrings(Class<? extends Object> clss){
		List <Field> fields = getAllFields(clss);
		List<String> returnList = new ArrayList<String>();
		fields.forEach((f) -> returnList.add(f.getName().toLowerCase()));
		Collections.sort(returnList, new Comparator<String>() {


			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
			
		});
		return returnList;
	}
	
	
	

}
