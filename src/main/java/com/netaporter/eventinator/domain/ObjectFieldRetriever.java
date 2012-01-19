package com.netaporter.eventinator.domain;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * User: gawain
 */
public class ObjectFieldRetriever<T> {

    Class annotationType;

    public ObjectFieldRetriever(Class annotationType) {
        this.annotationType = annotationType;
    }

    public T getFieldValue(Object object) {
        Object value = AnnotationHelper.getAnnotatedFieldValue(object, annotationType);
        return (T) value;
      }

}
