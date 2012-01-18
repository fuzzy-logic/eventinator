package com.netaporter.eventinator.domain;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * User: gawain
 */
public class VersionIncrementor<T> {

    private final String fieldName;

     public VersionIncrementor() {
        this.fieldName =  "version";
     }

     public VersionIncrementor(String fieldName) {
        this.fieldName =  fieldName;
     }

     public void incrementVersion(T object) {
        Field field = ReflectionUtils.findField(object.getClass(), fieldName);
         ReflectionUtils.makeAccessible(field);
         Integer value = (Integer) ReflectionUtils.getField(field, object);
         ReflectionUtils.setField(field, object, value + 1);
    }

}
