package org.zhire.work.model;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ClassUtils {

    public static String getClassVar(String className) {
        return className.substring(0, 1).toLowerCase() + className.substring(1);
    }

    public static List<Field> getAllFieldList(Class clazz) {
        List<Field> fieldList = new ArrayList();

        for (Class tempClass = clazz; tempClass != null; tempClass = tempClass.getSuperclass()) {
            fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
        }

        Iterator var3 = fieldList.iterator();

        while (var3.hasNext()) {
            Field field = (Field) var3.next();
            field.setAccessible(true);
        }

        return fieldList;
    }


    public static Map<String, Object> beanToMap(Object bean) {
        try {
            Map<String, Object> map = new LinkedHashMap();
            List<Field> fieldList = getAllFieldList(bean.getClass());
            Iterator var3 = fieldList.iterator();

            while (var3.hasNext()) {
                Field field = (Field) var3.next();
                String fieldName = field.getName();
                Object fieldValue = field.get(bean);
                map.put(fieldName, fieldValue);
            }

            return map;
        } catch (IllegalAccessException var7) {
            throw new RuntimeException(var7);
        }
    }



    private static String getValueString(Object bean, Field field) {
        try {
            return Optional.ofNullable(field.get(bean)).map(value -> (String) value).orElse("");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (IllegalAccessException | InstantiationException var2) {
            throw new RuntimeException(var2);
        }
    }

    /**
     * 构造指定异常
     *
     * @param code
     * @param desc
     * @param type
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T newException(int code, String desc, Class<T> type) {
        T t = null;
        try {
            t = type.getDeclaredConstructor(int.class, String.class).newInstance(code, desc);
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return t;
    }
}
