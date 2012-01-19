package com.netaporter.eventinator.repos;

import java.io.Serializable;

/**
 * User: gawain
 */
public interface DomainRepository {
    Object findOne(Class aClass, Serializable id);

    void delete(Class aClass, Serializable id);

    void save(Object object);
}
