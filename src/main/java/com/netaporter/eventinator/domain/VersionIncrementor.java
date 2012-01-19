package com.netaporter.eventinator.domain;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * User: gawain
 */
public class VersionIncrementor<T> {

    Class annotation = DomainVersion.class;


     public VersionIncrementor() { }


    public VersionIncrementor(Class annotation) {
        this.annotation = annotation;
    }

    public void incrementVersion(T object) {
        Field field = AnnotationHelper.getAnnotatedField(object, annotation);
         if (field == null) {
             throw new RuntimeException("No field '" + field + "' found on object " + object);
         }
         ReflectionUtils.makeAccessible(field);
         Integer value = (Integer) ReflectionUtils.getField(field, object);
         ReflectionUtils.setField(field, object, value + 1);
    }



}
