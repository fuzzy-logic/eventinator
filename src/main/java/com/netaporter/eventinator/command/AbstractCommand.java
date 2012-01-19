package com.netaporter.eventinator.command;

import com.netaporter.eventinator.domain.VersionIncrementor;
import com.netaporter.eventinator.event.Event;

/**
 * User: gawain
 */
public abstract class AbstractCommand<E extends Event, O> implements Command<O> {

    private final E event;
    private final O domainObject;
    VersionIncrementor incrementor = new VersionIncrementor();

/*    public AbstractCommand() {
      event = null;
      domainObject = null;
    }*/

    public AbstractCommand(E event, O domainObject) {
        this.event = event;
        this.domainObject = domainObject;
        this.incrementor = new VersionIncrementor();
    }

    public E getEvent() {
        return event;
    }

    public O getDomainObject() {
        return domainObject;
    }

     @Override
    public O apply() {
        E event = getEvent();
        O domainObject = getDomainObject();
        O newObject = applyCommand(event, domainObject);
        if (newObject != null) {
             //object is null if deleted. so check above
             //automagically increment the version number so no one forgets
            incrementor.incrementVersion(newObject);
        }
        return newObject;
    }

    protected abstract O applyCommand(E event, O domainObject);


}
