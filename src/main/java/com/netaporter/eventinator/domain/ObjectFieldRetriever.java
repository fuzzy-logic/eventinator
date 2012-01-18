package com.netaporter.eventinator.domain;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * User: gawain
 */
public class ObjectFieldRetriever<T> {

    String fieldName;

    public ObjectFieldRetriever(String idField) {
        this.fieldName = idField;
    }

    public T getFieldValue(Object object) {
        Field field = ReflectionUtils.findField(object.getClass(), fieldName);
        ReflectionUtils.makeAccessible(field);
        Object fieldObject = ReflectionUtils.getField(field, object);
        return (T) fieldObject;
      }

}
