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
	
	
	// ---- SUPPORTIN TOOLS FOR R/W ---- //
	
	// Get a list of all methods and filter them to get all getters
	public static List<Method> getGetters(Class<? extends Object> clss){
		List<Method> methodList = Arrays.stream(clss.getMethods()).filter(f -> (f.getName().startsWith("get")||f.getName().startsWith("is")) && !(f.getName().equals("getClass"))).collect(Collectors.toList());
		return methodList;
	}
	
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
		Collections.sort(result, new Comparator<Field>() {
			
			@Override
			public int compare(Field o1, Field o2) {
				return o1.getName().compareTo(o2.getName());
			}
			
		});
		return result;	
	}
	
	// Get getID method for nested objects
	public static Method getIdMethod(Class<? extends Object> clss) throws NoSuchMethodException, SecurityException{
		Method method = clss.getMethod("getId", (Class<?>[])null);
		return method;
	}
	
	// Map getters to respective fields
	public static HashMap<Field,Method> getGetterHash(Class<? extends Object> clss, List<Field> fields){
		HashMap<Field, Method> getterHash = new HashMap<Field, Method>();
		List<Method> methodList = ToolKit.getGetters(clss);
		List<Field> fieldList = fields;
		for (Field f: fieldList) {
			for (Method m: methodList) {
				if (m.getName().toLowerCase().contains(f.getName().toLowerCase())) {
					getterHash.put(f, m);
					break;
				}
			}
		}
		
		return getterHash;
	}
	
	// Map setters to respective fields
	public static HashMap<Field,Method> getSetterHash(Class<? extends Object> clss, List<Field> fields){
		HashMap<Field, Method> setterHash = new HashMap<Field, Method>();
		List<Method> methodList = ToolKit.getSetters(clss);
		List<Field> fieldList = fields;
		for (Field f: fieldList) {
			for (Method m: methodList) {
				if (m.getName().toLowerCase().contains(f.getName().toLowerCase().substring(2))) {
					setterHash.put(f, m);
					break;
				}
			}
		}
		
		return setterHash;
	}
	
	// Generate the line for writing to file
	public static String generateFileLine(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String fileFormat = "";
		List<Field> fields = ToolKit.getAllFields(obj.getClass());
		HashMap<Field, Method> allFields = ToolKit.getGetterHash(obj.getClass(), fields);
		for (Field f: fields) {
			Object value = allFields.get(f).invoke(obj);
			if (f.getType().getTypeName().startsWith("models")) {
				String id = getIdMethod(value.getClass()).invoke(value).toString(); // gets the getId method for specific object (no duck type?)
				fileFormat += id + "|";
			} else if (value instanceof HashMap){
				String allelements = "";
				Set<?> set = ((HashMap<?, ?>)value).keySet();
				for (Object s1: set) {
					allelements += s1 + ";";
				}
				fileFormat += allelements + "|";
			} else {
				fileFormat += value + "|";
			}
		}
		fileFormat += "\n";
		return fileFormat;
	}
		
	
	
	
	
	
	// Test Block
	
	
	public static Object objectFromArray(String[] array, Object object) {
		
		return object;
	}
	

	
	
	// DEPRECATED
	// generate fileLine for writing into text for specific object
//	public static String generateFileLineDEPRECATED(Object obj) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {
//		String fileFormat = "";
//		List<Method> methods = getGetters(obj.getClass());
//		Collections.sort(methods, new Comparator<Method>() {  // Sorted for consistent positioning
//
//			// Override for comparing Methods (default doesn't implement Comparable)
//			@Override
//			public int compare(Method o1, Method o2) {
//				return o1.getName().compareTo(o2.getName());
//			}
//		});
////		methods.forEach((el) -> System.out.println(el.getName().toString())); // Helper for coding positions
//		for (int i = 0; i< methods.size(); i++) { // Generating specific segment for the fileFormat
//			Method method = methods.get(i);
//			Object value = method.invoke(obj); // calls the method on the object, getting the value for the field
//			if (value.getClass().getPackageName().equalsIgnoreCase("models")) { // check for basic types vs custom types (Objects)
//				String id = getIdMethod(value.getClass()).invoke(value).toString(); // gets the getId method for specific object (no duck type?)
//				fileFormat += id + "|";
//			}else if(value instanceof HashMap){
//				String allelements = "";
//				Set<?> set = ((HashMap<?, ?>)value).keySet();
//				for (Object s1: set) {
//					allelements += s1 + ";";
//				}
//				fileFormat += allelements + "|";
//			}else {
//				fileFormat += value + "|";
//				
//			}
//			
//		}
//		fileFormat += "\n";
//		System.out.println(fileFormat);
//		return fileFormat;
//	}
	

	
	
	

}
