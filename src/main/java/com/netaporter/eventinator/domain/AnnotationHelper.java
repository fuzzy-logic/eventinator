package com.netaporter.eventinator.domain;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * User: gawain
 */
public class AnnotationHelper {


     public static Object getAnnotatedFieldValue(Object object, Class annotationType) {
        Class cls = object.getClass();
         Field[] fields = cls.getFields();
         Object value = null;


        try {
                for (Field field : fields) {
                      Annotation[] annotations = field.getDeclaredAnnotations();
                    for(Annotation annotation : annotations){
                        if(annotationType.isAssignableFrom(annotation.getClass())){
                             value = field.get(object);
                            return value;
                        }
                    }
                }
            }   catch (Exception e) {
              throw new RuntimeException("error trying to get field value for annotation " + annotationType + " on object" + object);
        }
        if (value == null) throw  new RuntimeException("error trying to get field value for annotation " + annotationType + " on object" + object);
        return value;
    }
}
