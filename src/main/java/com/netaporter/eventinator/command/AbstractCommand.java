package com.netaporter.eventinator.command;

import com.netaporter.eventinator.event.Event;
import com.netaporter.eventinator.model.DomainObject;

/**
 * User: gawain
 */
public abstract class AbstractCommand<E extends Event, O extends DomainObject> implements Command<O> {

    private final E event;
    private final O domainObject;
    VersionIncrementor incrementor;

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
        incrementor.incrementVersion(newObject); //automagically increment the version number so no one forgets
        return newObject;
    }

    protected abstract O applyCommand(E event, O domainObject);

}
