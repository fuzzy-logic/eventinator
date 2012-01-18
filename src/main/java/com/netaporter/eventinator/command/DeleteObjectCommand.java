package com.netaporter.eventinator.command;

import com.netaporter.eventinator.command.AbstractCommand;
import com.netaporter.eventinator.event.Event;

/**
 *
 * can be used to delete any domain object
 *
 * User: gawain
 */
public class DeleteObjectCommand extends AbstractCommand<Event, Object> {
    public DeleteObjectCommand(Event event, Object domainObject) {
        super(event, domainObject);
    }

    @Override
    protected Object applyCommand(Event event, Object domainObject) {
       return null;
    }
}
