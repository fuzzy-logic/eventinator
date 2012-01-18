package com.netaporter.eventinator.event;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * User: gawain
 */
public abstract class AbstractEvent<T> implements Event<T> {

    @Id
    String eventId;
    String creatorEmail;
    Date creationDate;
    Date serverReceiveDate;
    int domainObjectVersion;
    String domainObjectId;

    public AbstractEvent() {
        // we set server receive date here because this object will only be created on the server,
        // so it should be the date received
         serverReceiveDate = new Date();
        eventId = UUID.randomUUID().toString();
    }



    @Override
    public Serializable getDomainObjectId() {
        return domainObjectId;
    }

    public void setCreatorEmail(String creatorEmail) {
        this.creatorEmail = creatorEmail;
    }

    public void setDomainObjectId(String domainObjectId) {
        this.domainObjectId = domainObjectId;
    }

    public String getEventId() {
        return eventId;
    }

    public String getCreatorEmail() {
        return creatorEmail;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getServerReceiveDate() {
        return serverReceiveDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getDomainObjectVersion() {
        return domainObjectVersion;
    }

    public void setDomainObjectVersion(int domainObjectVersion) {
        this.domainObjectVersion = domainObjectVersion;
    }
}
