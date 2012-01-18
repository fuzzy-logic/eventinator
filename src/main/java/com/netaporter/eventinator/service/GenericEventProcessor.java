package com.netaporter.eventinator.service;

import com.netaporter.eventinator.command.Command;
import com.netaporter.eventinator.event.AbstractEvent;
import com.netaporter.eventinator.event.Event;
import com.netaporter.eventinator.factory.CommandFactory;
import com.netaporter.eventinator.model.AbstractDomainObject;
import com.netaporter.eventinator.model.DomainObject;
import com.netaporter.eventinator.repos.CommonRepository;
import com.netaporter.eventinator.event.EventComparator;
import com.netaporter.eventinator.repos.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.*;


/**
 * User: gawain
 */
public class GenericEventProcessor implements EventProcessor {

    private final static Logger log = LoggerFactory.getLogger(GenericEventProcessor.class);

    CommandFactory commandFactory;

    @Autowired
    @Qualifier("commonRepository")
    CommonRepository domainObjectRepository;

    @Autowired
    @Qualifier("eventRepository")
    EventRepository eventRepository;


    @Override
    public boolean handleEvent(Event event) {
        AbstractDomainObject existingObject = domainObjectRepository.findOne(event.getDomainObjectClass(), event.getDomainObjectId());
        AbstractDomainObject modifiedObject =   applyEvent(event, existingObject);
        domainObjectRepository.save(modifiedObject);
        eventRepository.save((AbstractEvent) event);
        return true;

    }

    private AbstractDomainObject applyEvent(Event event, AbstractDomainObject object) {
        AbstractDomainObject newObject = null;
        if ( eventVersionNumberConflict(event, object) ) {
            Event[] eventArray = loadExistingEventsForObject(event, object);
            newObject = applyMultipleEvents(object, eventArray);
        }  else {
            newObject = applyMultipleEvents(object, event);
        }
        return newObject;
    }




    private AbstractDomainObject applyMultipleEvents(DomainObject object, Event... allEvents) {
        Collections.sort(Arrays.asList(allEvents), new EventComparator());
        AbstractDomainObject newObject = null;
        for (Event e : allEvents) {
            log.debug("Applying Event " + e);
            Command<AbstractDomainObject> command = commandFactory.createCommand(e, object);
            log.debug("New Object's state " + newObject);
            newObject = command.apply();
        }
        return newObject;
    }

    private Event[] loadExistingEventsForObject(Event currentEvent, AbstractDomainObject object) {
        List<AbstractEvent> allEvents = eventRepository.getByDomainObjectId(object.getId());
        allEvents.add((AbstractEvent) currentEvent);
        Event[] eventArray = allEvents.toArray(new Event[allEvents.size()]);
        return eventArray;
    }

    private boolean eventVersionNumberConflict(Event event, AbstractDomainObject object) {
        if (object == null) return false;
        boolean hasVersionConflict = (object.getVersion() > event.getDomainObjectVersion());
        return hasVersionConflict;
    }

    public void setCommandFactory(CommandFactory factory) {
        this.commandFactory = factory;
    }

    public void setDomainObjectRepository(CommonRepository domainObjectRepository) {
        this.domainObjectRepository = domainObjectRepository;
    }

    public void setEventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


}
