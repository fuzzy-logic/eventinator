package com.netaporter.eventinator.event;

import java.io.Serializable;
import java.util.Date;

/**
 * User: gawain
 */
public interface Event<T> {

    Class getDomainObjectClass();

    Serializable getDomainObjectId();

    Class getCommandClass();

    public String getEventId();

    public String getCreatorEmail();

    public Date getCreationDate();

    public Date getServerReceiveDate();

     public int getDomainObjectVersion();

}
