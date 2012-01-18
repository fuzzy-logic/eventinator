package com.netaporter.eventinator.repos;

import com.netaporter.eventinator.model.AbstractDomainObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

/**
 * User: gawain
 */
public class CommonRepository {

    @Autowired
    ApplicationContext ctx;

    public AbstractDomainObject findOne(Class aClass, Serializable id) {
         AbstractDomainObject o = (AbstractDomainObject) getCrudRepository(aClass).findOne(id.toString());
        return o;
    }

     public void delete(Class aClass, Serializable id) {
         getCrudRepository(aClass).delete(id.toString());
    }

     public void save(Object object) {
         getCrudRepository(object.getClass()).save(object);
    }

    private CrudRepository getCrudRepository(Class aClass) {
        return (CrudRepository) ctx.getBean(aClass.getSimpleName().toLowerCase() + "Repository");
    }
}
