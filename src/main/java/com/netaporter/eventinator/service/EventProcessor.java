package com.netaporter.eventinator.service;

import com.netaporter.eventinator.event.Event;

/**
 * User: gawain
 */
public interface EventProcessor {

    /**
     *
     *
     * @param event
     * @return event status (boolean is a crap return type, this should be something that wrap the status with the
     * potential to have enough data to resolve event conflicts).
     */
    public boolean handleEvent(Event event);
}
