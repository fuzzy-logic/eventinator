package com.netaporter.eventinator.repos;

import com.netaporter.eventinator.event.Event;
import com.netaporter.eventinator.test.customer.events.ChangeCustomerEmailEvent;
import com.netaporter.eventinator.test.customer.events.ChangeCustomerPasswordEvent;
import com.netaporter.eventinator.test.customer.events.NewCustomerEvent;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;


/**
 * User: gawain
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-data.xml")
public class EventRepositoryIntegrationTest {

    @Autowired
    EventRepository eventRepository;

    @Test
    public void testEventRepos() {


        NewCustomerEvent newCustEvent = new NewCustomerEvent();
        newCustEvent.setDomainObjectId(null);
        newCustEvent.setCreationDate(new Date());
        newCustEvent.setCreatorEmail("me@me.com");
        newCustEvent.setDomainObjectVersion(0);
        newCustEvent.setNewDob("10/11/2012");
        newCustEvent.setNewEmail("jim@jim.com");
        newCustEvent.setNewName("jim");
        newCustEvent.setNewPassword("asdhjcbasd");

        eventRepository.save(newCustEvent);


        ChangeCustomerEmailEvent changeCustEmailEvent = new ChangeCustomerEmailEvent();
        changeCustEmailEvent.setDomainObjectId("123");
        changeCustEmailEvent.setCreationDate(new Date());
        changeCustEmailEvent.setCreatorEmail("me@me.com");
        changeCustEmailEvent.setDomainObjectVersion(1);
        changeCustEmailEvent.setNewEmail("jim@jim.com");

        eventRepository.save(changeCustEmailEvent);


        ChangeCustomerPasswordEvent changeCustPwdEvent = new ChangeCustomerPasswordEvent();
        changeCustPwdEvent.setDomainObjectId("123");
        changeCustPwdEvent.setCreationDate(new Date());
        changeCustPwdEvent.setCreatorEmail("me@me.com");
        changeCustPwdEvent.setDomainObjectVersion(1);
        changeCustPwdEvent.setNewPassword("jim@jim.com");

        eventRepository.save(changeCustPwdEvent);


        Event e1 = eventRepository.findOne(newCustEvent.getEventId());
        Event e2 = eventRepository.findOne(changeCustEmailEvent.getEventId());
        Event e3 = eventRepository.findOne(changeCustPwdEvent.getEventId());


        Assert.assertEquals(newCustEvent.getDomainObjectId(), e1.getDomainObjectId());
        Assert.assertEquals(newCustEvent.getCreationDate(), e1.getCreationDate());
        Assert.assertEquals(newCustEvent.getEventId(), e1.getEventId());
        Assert.assertEquals(newCustEvent.getCommandClass(), e1.getCommandClass());
        Assert.assertEquals(newCustEvent.getCreatorEmail(), e1.getCreatorEmail());
        Assert.assertEquals(newCustEvent.getDomainObjectClass(), e1.getDomainObjectClass());
        Assert.assertEquals(newCustEvent.getServerReceiveDate(), e1.getServerReceiveDate());
        Assert.assertEquals(newCustEvent.getClass(), e1.getClass());

        Assert.assertEquals(changeCustEmailEvent.getDomainObjectId(), e2.getDomainObjectId());
        Assert.assertEquals(changeCustEmailEvent.getCreationDate(), e2.getCreationDate());
        Assert.assertEquals(changeCustEmailEvent.getEventId(), e2.getEventId());
        Assert.assertEquals(changeCustEmailEvent.getCommandClass(), e2.getCommandClass());
        Assert.assertEquals(changeCustEmailEvent.getCreatorEmail(), e2.getCreatorEmail());
        Assert.assertEquals(changeCustEmailEvent.getDomainObjectClass(), e2.getDomainObjectClass());
        Assert.assertEquals(changeCustEmailEvent.getServerReceiveDate(), e2.getServerReceiveDate());
        Assert.assertEquals(changeCustEmailEvent.getClass(), e2.getClass());

        Assert.assertEquals(changeCustPwdEvent.getDomainObjectId(), e3.getDomainObjectId());
        Assert.assertEquals(changeCustPwdEvent.getCreationDate(), e3.getCreationDate());
        Assert.assertEquals(changeCustPwdEvent.getEventId(), e3.getEventId());
        Assert.assertEquals(changeCustPwdEvent.getCommandClass(), e3.getCommandClass());
        Assert.assertEquals(changeCustPwdEvent.getCreatorEmail(), e3.getCreatorEmail());
        Assert.assertEquals(changeCustPwdEvent.getDomainObjectClass(), e3.getDomainObjectClass());
        Assert.assertEquals(changeCustPwdEvent.getServerReceiveDate(), e3.getServerReceiveDate());
        Assert.assertEquals(changeCustPwdEvent.getClass(), e3.getClass());


    }
}
