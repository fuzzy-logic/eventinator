package com.netaporter.eventinator.factory;

import com.netaporter.eventinator.command.Command;
import com.netaporter.eventinator.event.Event;

/**
 * User: gawain
 */
public interface CommandFactory {

    public Command createCommand(Event event, Object domainObject);
}
