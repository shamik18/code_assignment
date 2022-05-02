package com.mycomp.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReflectionUtils {
    private ReflectionUtils(){}
   public static <T> String invoke(Class<? extends Object> tClass,String fieldName, T instance){
       try {
           Method method  = tClass.getDeclaredMethod(fieldName);
           return (String) method.invoke(instance);
       } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
           throw new RuntimeException(e);
       }
   }

    public static <T> String[] getFields(Class<T> tClass) {
        Field[] fields = tClass.getDeclaredFields();
        List<String> fieldList = Arrays.stream(fields).map(field -> field.getName()).collect(Collectors.toList());
        return fieldList.toArray(new String[]{});
    }
}
