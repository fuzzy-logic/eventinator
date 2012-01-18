package com.netaporter.eventinator.model;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * User: gawain
 */
public abstract class AbstractDomainObject implements DomainObject {

    @Id
    protected final Serializable id;
    protected int version;
    protected boolean deleted = false;

    public AbstractDomainObject() {
       id = null;
    }

    protected AbstractDomainObject(Serializable id) {
         this.id = id;
    }


    public Serializable getId() {
        return id;
    }


    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void markDeleted() {
        deleted = true;
    }

    public void markNotDeleted() {
        deleted = false;
    }
}
