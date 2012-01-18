package com.netaporter.eventinator.event;

import java.util.Comparator;

public class EventComparator implements Comparator<Event> {

        @Override
        public int compare(Event event, Event event1) {
            if (event.getDomainObjectVersion() == event1.getDomainObjectVersion()) {
                return event.getServerReceiveDate().compareTo(event1.getServerReceiveDate());
            }
            return event.getDomainObjectVersion() - event1.getDomainObjectVersion();
        }
    }