package com.netaporter.eventinator.event;

import com.netaporter.eventinator.event.Event;
import com.netaporter.eventinator.event.EventComparator;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * User: gawain
 */
public class EventComparatorUnitTest {


    @Test
    public void testCompareVersions() {
        EventComparator comparator = new EventComparator();


        Event e1 = Mockito.mock(Event.class);
        Event e2 = Mockito.mock(Event.class);
        Event e3 = Mockito.mock(Event.class);
        Event e4 = Mockito.mock(Event.class);
        Event e5 = Mockito.mock(Event.class);

        Mockito.when(e1.getDomainObjectVersion()).thenReturn(1);
        Mockito.when(e2.getDomainObjectVersion()).thenReturn(2);
        Mockito.when(e3.getDomainObjectVersion()).thenReturn(3);
        Mockito.when(e4.getDomainObjectVersion()).thenReturn(4);
        Mockito.when(e5.getDomainObjectVersion()).thenReturn(5);

        List<Event> events = Arrays.asList(e3,e1,e4,e2,e5);
        Collections.sort(events, comparator );

        Assert.assertEquals(e1, events.get(0));
        Assert.assertEquals(e2, events.get(1));
        Assert.assertEquals(e3, events.get(2));
        Assert.assertEquals(e4, events.get(3));
        Assert.assertEquals(e5, events.get(4));

    }

      @Test
    public void testCompareVersionsAndDates() throws Exception {
        EventComparator comparator = new EventComparator();

          Date date1 = new Date();

          Thread.sleep(1000);

          Date date2 = new Date();


        Event e1 = Mockito.mock(Event.class);
        Event e2 = Mockito.mock(Event.class);
        Event e3 = Mockito.mock(Event.class);
        Event e4 = Mockito.mock(Event.class);
        Event e5 = Mockito.mock(Event.class);

        Mockito.when(e1.getDomainObjectVersion()).thenReturn(1);
        Mockito.when(e2.getDomainObjectVersion()).thenReturn(2);
        Mockito.when(e2.getServerReceiveDate()).thenReturn(date1);
        Mockito.when(e3.getDomainObjectVersion()).thenReturn(2);
        Mockito.when(e3.getServerReceiveDate()).thenReturn(date2);
        Mockito.when(e4.getDomainObjectVersion()).thenReturn(4);
        Mockito.when(e4.getServerReceiveDate()).thenReturn(date1);
        Mockito.when(e5.getDomainObjectVersion()).thenReturn(4);
        Mockito.when(e5.getServerReceiveDate()).thenReturn(date2);

        List<Event> events = Arrays.asList(e3,e1,e4,e2,e5);
        Collections.sort(events, comparator );

        Assert.assertEquals(e1, events.get(0));
        Assert.assertEquals(e2, events.get(1));
        Assert.assertEquals(e3, events.get(2));
        Assert.assertEquals(e4, events.get(3));
        Assert.assertEquals(e5, events.get(4));

    }
}
