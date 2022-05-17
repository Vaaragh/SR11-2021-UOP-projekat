package tools;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ToolKit {
	
	// Constructor
	
	// Methods	
	
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
	
	public static List<Method> getMethods(Class clss){
		List<Method> methodList = Arrays.stream(clss.getMethods()).filter(f -> (f.getName().startsWith("g")||f.getName().startsWith("i")) && !(f.getName().equals("getClass"))).collect(Collectors.toList());
		return methodList;
	}
	
	public static Method getIdMethod(Class clss){
		List<Method> methodList = Arrays.stream(clss.getMethods()).filter(f -> (f.getName().equals("getId"))).collect(Collectors.toList());
		Method method = methodList.get(0);
		return method;
	}
	
	public static String generateFileLine(Object obj) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String fileFormat = "";
		List<Method> methods = getMethods(obj.getClass());
		Collections.sort(methods, new Comparator<Method>() {

			@Override
			public int compare(Method o1, Method o2) {
				return o1.hashCode() - o2.hashCode();
			}
		});
		methods.forEach((el) -> System.out.println(el.getName().toString()));
		for (int i = 0; i< methods.size(); i++) {
//			System.out.println(fi.get(i));   Check
			Method x = methods.get(i);
//			System.out.println(x.invoke(obj));   Check
			Object s = x.invoke(obj);
			if (s.getClass().getPackageName().equalsIgnoreCase("models")) {
				String id = getIdMethod(s.getClass()).invoke(s).toString();
				fileFormat += id + "|";
//				System.out.println("da  ---  "+ id +"-----------" + x.getReturnType().toString()); // Check
			}else {
				fileFormat += s + "|";
//				System.out.println("ne  ---  " +s +"-----------" + x.getReturnType().toString()); // Check
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
