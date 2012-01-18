package com.netaporter.eventinator.service;

import com.netaporter.eventinator.command.Command;
import com.netaporter.eventinator.domain.ObjectFieldRetriever;
import com.netaporter.eventinator.event.AbstractEvent;
import com.netaporter.eventinator.event.Event;
import com.netaporter.eventinator.factory.CommandFactory;
import com.netaporter.eventinator.repos.DomainRepository;
import com.netaporter.eventinator.event.EventComparator;
import com.netaporter.eventinator.repos.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.Serializable;
import java.util.*;


/**
 * User: gawain
 */
public class GenericEventProcessor implements EventProcessor {

    private final static Logger log = LoggerFactory.getLogger(GenericEventProcessor.class);

    CommandFactory commandFactory;

    @Autowired
    @Qualifier("domainRepository")
    DomainRepository domainObjectRepository;

    @Autowired
    @Qualifier("eventRepository")
    EventRepository eventRepository;

    ObjectFieldRetriever<Serializable> idRetriever = new ObjectFieldRetriever<Serializable>("id");
    ObjectFieldRetriever<Integer> versionRetriever = new ObjectFieldRetriever<Integer>("version");



    @Override
    public boolean handleEvent(Event event) {
        Object existingObject = domainObjectRepository.findOne(event.getDomainObjectClass(), event.getDomainObjectId());
        Object modifiedObject =   applyEvent(event, existingObject);
        if (modifiedObject == null) {
            Serializable existingObjectId = idRetriever.getFieldValue(existingObject);
            domainObjectRepository.delete(existingObject.getClass(), existingObjectId);
        }  else {
            domainObjectRepository.save(modifiedObject);
        }
        eventRepository.save((AbstractEvent) event);
        return true;

    }

    private Object applyEvent(Event event, Object object) {
        Object newObject = null;
        if ( eventVersionNumberConflict(event, object) ) {
            Event[] eventArray = loadExistingEventsForObject(event, object);
            newObject = applyMultipleEvents(object, eventArray);
        }  else {
            newObject = applyMultipleEvents(object, event);
        }
        return newObject;
    }




    private Object applyMultipleEvents(Object object, Event... allEvents) {
        Collections.sort(Arrays.asList(allEvents), new EventComparator());
        Object newObject = null;
        for (Event e : allEvents) {
            log.debug("Applying Event " + e);
            Command<Object> command = commandFactory.createCommand(e, object);
            log.debug("New Object's state " + newObject);
            newObject = command.apply();
        }
        return newObject;
    }

    private Event[] loadExistingEventsForObject(Event currentEvent, Object object) {
        Serializable id = idRetriever.getFieldValue(object);
        List<AbstractEvent> allEvents = eventRepository.getByDomainObjectId(id);
        allEvents.add((AbstractEvent) currentEvent);
        Event[] eventArray = allEvents.toArray(new Event[allEvents.size()]);
        return eventArray;
    }

    private boolean eventVersionNumberConflict(Event event, Object object) {
        if (object == null) return false;
        Integer version = versionRetriever.getFieldValue(object);
        boolean hasVersionConflict = (version > event.getDomainObjectVersion());
        return hasVersionConflict;
    }

    public void setCommandFactory(CommandFactory factory) {
        this.commandFactory = factory;
    }

    public void setDomainObjectRepository(DomainRepository domainObjectRepository) {
        this.domainObjectRepository = domainObjectRepository;
    }

    public void setEventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


}
