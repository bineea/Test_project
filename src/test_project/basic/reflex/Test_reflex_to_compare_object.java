package test_project.basic.reflex;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.lang.NonNull;

/**
 * 利用反射获取对象属性名称及属性值，并进行比较
 * 
 * 1.如果为同一类的不同对象，其字段名称、类型、顺序完全一致；可以循环获取对象属性完成比对；
 * 2.如果为不同类的不同对象，其字段名称、类型、顺序可能完全不同，如何处理？
 * @author bineea
 *
 */
public class Test_reflex_to_compare_object {

	/**
	 * 实现较粗糙
	 * @param based
	 * @param compared
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@Deprecated
	public Map<Field, Object> toCompareData(Object based, Object compared) throws IllegalArgumentException, IllegalAccessException {
		Map<Field, Object> compareResult = new HashMap<Field, Object>();
		Class<?> basedClass = based.getClass();
		Class<?> comparedClass = compared.getClass();
		Class<?> basedSuperclass = basedClass.getSuperclass();
		Class<?> comparedSuperclass = comparedClass.getSuperclass();
		
		List<Field> basedFields = new ArrayList<Field>(Arrays.asList(basedClass.getDeclaredFields()));
		List<Field> comparedFields = new ArrayList<Field>(Arrays.asList(comparedClass.getDeclaredFields()));
		while(basedSuperclass != null) {
			basedFields.addAll(Arrays.asList(basedSuperclass.getDeclaredFields()));
			basedSuperclass = basedSuperclass.getSuperclass();
		}
		while(comparedSuperclass != null) {
			comparedFields.addAll(Arrays.asList(comparedSuperclass.getDeclaredFields()));
			comparedSuperclass = comparedSuperclass.getSuperclass();
		}
		for(Field baseField : basedFields)
		{
			baseField.setAccessible(true);
			boolean matched = false;
			if(comparedFields.stream().map(f -> f.getName()).collect(Collectors.toList()).contains(baseField.getName()))
			{
				for(Field comparedField : comparedFields)
				{
					comparedField.setAccessible(true);
					if(baseField.getName().equals(comparedField.getName()))
					{
						if(baseField.get(based) == comparedField.get(compared))
							matched = true;
						else if(baseField.get(based) == null || comparedField.get(compared) == null)
							matched = false;
						else
							matched = baseField.get(based).equals(comparedField.get(compared));
						break;
					}
				}
			}
			if(!matched)
				compareResult.put(baseField, baseField.get(based));
		}
		return compareResult;
	}
	
	/**
	 * 优化toCompareData方法，通过map避免嵌套循环
	 */
	private static Map<Field, Object>  compareProperties(Object based, Object compared, String... ignoreProperties) {
        Map<Field, Object> compareResult = new HashMap<Field, Object>();
        try {
        	if (based == null || compared == null) {
        		return compareResult;
        	}
        	
        	List<Field> basedFields = new ArrayList<Field>();
        	 List<Field> comparedFields = new ArrayList<Field>();
            for (Class<?> basedClass = based.getClass(); basedClass != Object.class; basedClass = basedClass.getSuperclass()) {
            	basedFields.addAll(Arrays.asList(basedClass.getDeclaredFields()));
            }
            
            for (Class<?> comparedClass = compared.getClass(); comparedClass != Object.class; comparedClass = comparedClass.getSuperclass()) {
            	comparedFields.addAll(Arrays.asList(comparedClass.getDeclaredFields()));
            }
            
            Map<String, Field> basedFieldMap = basedFields.stream().collect(Collectors.toMap(field -> field.getName(), field -> field, (k1, k2) -> k2));
            Map<String, Field> comparedFieldMap = comparedFields.stream().collect(Collectors.toMap(field -> field.getName(), field -> field, (k1, k2) -> k2));

            Set<String> basedFieldNameSet = basedFieldMap.keySet();
            Set<String> comparedFieldNameSet = comparedFieldMap.keySet();
            Set<String> ignorePropertySet = new HashSet<>(Arrays.asList(ignoreProperties));

            basedFieldNameSet.retainAll(comparedFieldNameSet);
            basedFieldNameSet.removeAll(ignorePropertySet);
            if (basedFieldNameSet.size() <= 0) {
                return compareResult;
            }
            for (String basedFieldName : basedFieldNameSet) {
                Field basedField = basedFieldMap.get(basedFieldName);
                basedField.setAccessible(true);
                Field comparedField = comparedFieldMap.get(basedFieldName);
                comparedField.setAccessible(true);
                if(!Objects.equals(basedField.get(based), comparedField.get(compared))) {
                    compareResult.put(basedField, basedField.get(based));
                }
            }
            return compareResult;
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
	
	public static void main(String[] args) {
		try {
			Test_reflex_to_compare_object test = new Test_reflex_to_compare_object();
			Test_A test_a = new Test_A("test_name", "test_idCode");
			Test_B test_b = new Test_B("test_name", "test_idCode", LocalDateTime.now());
			Map<Field, Object> result = test.compareProperties(test_a, test_b);
			for(Field field : result.keySet())
			{
				System.out.println(field.getAnnotation(NonNull.class));
				if(field.getAnnotation(NonNull.class) != null)
					System.out.println(field.getAnnotation(NonNull.class).toString());
				System.out.println("信息不一致，based信息属性："+field.getName()+"；内容："+String.valueOf(result.get(field)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

class Test_C {
	protected String test;

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
}

class Test_A extends Test_C{
	private String id;
	@NonNull
	private String name;
	@NonNull
	private String idCode;
	private LocalDateTime createTime;
	
	public Test_A(String name, String idCode) {
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.idCode = idCode;
		this.createTime = LocalDateTime.now();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

}

class Test_B {
	private LocalDateTime createTime;
	private String idCode;
	private String name;
	private String id;

	public Test_B (String name, String idCode, LocalDateTime createTime) {
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.idCode = idCode;
		this.createTime = createTime;
	}
	
	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}