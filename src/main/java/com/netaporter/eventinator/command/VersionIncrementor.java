package com.netaporter.eventinator.command;

import java.lang.reflect.Field;

/**
 * User: gawain
 */
public class VersionIncrementor<T> {

    private final String fieldName;

     VersionIncrementor() {
        this.fieldName =  "version";
     }

     VersionIncrementor(String fieldName) {
        this.fieldName =  fieldName;
     }

     public void incrementVersion(T object) {
        Field field = getFieldFromClass(object);
        incrementIntegerField(object, field);

    }

    private Field getFieldFromClass(Object object) {
        Class aClass = object.getClass();
        while(true) {
            try {
                Field field = aClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field;
            } catch (NoSuchFieldException e) {
                // it's fine, keep trying...
                System.out.println("can't find field '" + fieldName +  "' on class " + aClass + " ");
                aClass = aClass.getSuperclass();
                if (aClass == null) {
                    throw new RuntimeException("while trying to find field '"  + fieldName + "' no more superclasses for object " + object);
                }
            }
            catch (Exception e) {
               throw new RuntimeException("error trying to find field '" + fieldName + "' on class " + aClass + " on object " + object, e);
            }

        }


    }

    private void incrementIntegerField(Object object, Field field) {
        try {
             int version = field.getInt(object);
             field.setInt(object, version + 1);
              int newVersion = field.getInt(object);
              if (newVersion - 1 !=  version) {
                  throw new IllegalStateException("Version number did not increment as expected during reflection");
              }

        }   catch (Exception e) {
            throw new RuntimeException("Can't increment field '" + field.getName() + "' on object " + object, e);
        }

    }
}
