package com.lawencon.bootcamptest.util;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class AssignUtil {
    
    private Field[] fields;
    private Object[] obj;

    public Field[] getFields() {
        return fields;
    }
    public void setFields(Field[] fields) {
        this.fields = fields;
    }
    public Object[] getObj() {
        return obj;
    }
    public void setObj(Object[] obj) {
        this.obj = obj;
    }

    public void objToClass(Object[] obj, Object objClass) throws IllegalArgumentException, IllegalAccessException{
        setObj(obj);

        Class<?> class1 = objClass.getClass();
        Field[] fields = class1.getDeclaredFields();

        setFields(fields);

        if(isEqual()){
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];

                Class<?> typeClass = field.getType();

                field.setAccessible(true);
                if(Optional.ofNullable(obj[i]).isPresent())
                    field.set(objClass, typeClass.cast(obj[i]));
            }
        }else
            throw new IllegalArgumentException("field and object is not same");
        
    }

    public Map<String, Object> classToMap(Object objClass) throws IllegalArgumentException, IllegalAccessException{
        Class<?> objClazz = objClass.getClass();
        Field[] fields = objClazz.getDeclaredFields();

        Map<String, Object> dataMap = new LinkedHashMap<>();

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];

            Class<?> typeClass = field.getType();

            field.setAccessible(true);

            Object data = field.get(objClazz);

            if(Optional.ofNullable(data).isPresent())
                dataMap.put(field.getName(), typeClass.cast(data));
        }

        if(dataMap.size() == 0)
            throw new IllegalArgumentException("Data is'nt available");

        return dataMap;
    }

    public boolean isEqual(){
        return fields.length == obj.length;
    }

}
