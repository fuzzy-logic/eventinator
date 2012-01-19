package com.netaporter.eventinator.domain;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * User: gawain
 */
public class AnnotationHelper {


     public static Field getAnnotatedField(Object object, Class annotationType) {
        Class cls = object.getClass();
         Field[] fields = cls.getDeclaredFields();

        try {
                for (Field field : fields) {
                    ReflectionUtils.makeAccessible(field);
                    Annotation annotation = field.getAnnotation(annotationType);
                    if (annotation != null) return field;

                }
            }   catch (Exception e) {
              throw new RuntimeException("error trying to get field for annotation " + annotationType + " on object" + object);
        }
        throw  new RuntimeException("error trying to get field for annotation " + annotationType + " on object" + object);

    }


    public static Object getAnnotatedFieldValue(Object object, Class annotationType) {
       Field field =  getAnnotatedField(object, annotationType);
        Object value = null;
        try {
            value = field.get(object);
        } catch (Exception e) {
            throw new RuntimeException("error trying to get field value for annotation " + annotationType + " on object" + object);
        }
        if (value == null) throw  new RuntimeException("error trying to get field value for annotation " + annotationType + " on object" + object);
        return value;
    }


}
