package com.netaporter.eventinator.factory;

import com.netaporter.eventinator.command.Command;
import com.netaporter.eventinator.event.Event;
import com.netaporter.eventinator.model.DomainObject;

/**
 * User: gawain
 */
public interface CommandFactory {

    public Command createCommand(Event event, DomainObject domainObject);
}
