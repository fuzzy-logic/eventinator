package com.netaporter.eventinator.service;


import com.netaporter.eventinator.repos.EventRepository;
import com.netaporter.eventinator.test.customer.events.ChangeCustomerNameEvent;
import com.netaporter.eventinator.test.customer.events.DeleteCustomerEvent;
import com.netaporter.eventinator.test.customer.events.NewCustomerEvent;
import com.netaporter.eventinator.test.customer.model.Customer;
import com.netaporter.eventinator.test.customer.repositories.CustomerRepository;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * User: gawain
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/eventinator-context.xml", "classpath:/test-customer-domain-context.xml"})
public class GenericEventProcessorIntegrationTest {

    @Autowired
    EventProcessor customerEventHandler;

    @Autowired
    //@Qualifier("customerRepository")
    CustomerRepository customerRepository;

    @Autowired
    @Qualifier("eventRepository")
    EventRepository eventRepository;

    @Before
    public void setup() {
        customerRepository.deleteAll();
        eventRepository.deleteAll();
    }


    @Test
    public void testNewCustomerShouldBeCreated() {
        NewCustomerEvent event = new NewCustomerEvent();
        event.setNewName("new name");
        event.setNewDob("10/04/1928");
        event.setNewEmail("test@test.com");
        event.setNewPassword("changeme");
        event.setDomainObjectId("123");
        boolean eventOk = customerEventHandler.handleEvent(event);
        Customer customer = customerRepository.findOne("123");
        Assert.assertTrue(eventOk);
        Assert.assertNotNull(customer);
    }

    @Test
    public void testCustomerShouldBeDeleted() {
        NewCustomerEvent event = new NewCustomerEvent();
        event.setNewName("new name");
        event.setNewDob("10/04/1928");
        event.setNewEmail("test@test.com");
        event.setNewPassword("changeme");
        event.setDomainObjectId("123");
        boolean eventOk = customerEventHandler.handleEvent(event);
        Customer customer = customerRepository.findOne("123");
        Assert.assertTrue(eventOk);
        Assert.assertNotNull(customer);


        DeleteCustomerEvent deleteEvent = new DeleteCustomerEvent();
        deleteEvent.setDomainObjectId("123");
        boolean deleteEventOk = customerEventHandler.handleEvent(deleteEvent);
        customer = customerRepository.findOne("123");
        Assert.assertTrue(eventOk);
        Assert.assertNull(customer);
    }


    @Test
    public void testOutOfSequenceEventsShouldBeAppliedBackInOrder() {
        String id = "123";
        String dob = "10/04/1928";
        String password = "changeme";
        String email = "test@test.com";
        String name1 = "new name";
        String name2 = "name2";
        String name3 = "new out of sequence name acting on v2";
        String name4 = "new out of sequence name acting on v4";

        NewCustomerEvent event1 = new NewCustomerEvent();
        event1.setNewName(name1);
        event1.setNewDob(dob);
        event1.setNewEmail(email);
        event1.setNewPassword(password);
        event1.setDomainObjectId(id);
        event1.setCreationDate(new Date());
        event1.setCreatorEmail("gawain.hammond@gmail.com");
        boolean event1_OK = customerEventHandler.handleEvent(event1);
        Assert.assertTrue(event1_OK);
        Customer customer = customerRepository.findOne(id);
        Assert.assertNotNull(customer);
        Assert.assertEquals(1, customer.getVersion());
        Assert.assertEquals(email, customer.getEmailAddress());
        Assert.assertEquals(name1, customer.getName());
        Assert.assertEquals(password, customer.getPassword());
        Assert.assertEquals(dob, customer.getDob());

        ChangeCustomerNameEvent event2 = new ChangeCustomerNameEvent();
        event2.setNewName(name2);
        event2.setDomainObjectId(id);
        event2.setDomainObjectVersion(2);
        event2.setCreatorEmail("gawain.hammond@gmail.com");
        boolean event2_OK = customerEventHandler.handleEvent(event2);
        customer = customerRepository.findOne(id);
        Assert.assertTrue(event2_OK);
        Assert.assertEquals(2, customer.getVersion());
        Assert.assertEquals(name2, customer.getName());
        Assert.assertEquals(password, customer.getPassword());
        Assert.assertEquals(email, customer.getEmailAddress());
        Assert.assertEquals(dob, customer.getDob());

        // Out of sequence event acting on Version 1
        ChangeCustomerNameEvent event3 = new ChangeCustomerNameEvent();
        event3.setNewName(name3);
        event3.setDomainObjectVersion(1);
        event3.setDomainObjectId(id);
        event3.setCreatorEmail("gawain.hammond@gmail.com");
        boolean event3_OK = customerEventHandler.handleEvent(event3);
        customer = customerRepository.findOne(id);
        Assert.assertTrue(event3_OK);
        Assert.assertEquals(3, customer.getVersion());
        Assert.assertEquals(email, customer.getEmailAddress());
        Assert.assertEquals(name2, customer.getName());
        Assert.assertEquals(password, customer.getPassword());
        Assert.assertEquals(dob, customer.getDob());

        ChangeCustomerNameEvent event4 = new ChangeCustomerNameEvent();
        event4.setNewName(name4);
        event4.setDomainObjectVersion(4);
        event4.setDomainObjectId(id);
        event4.setCreatorEmail("gawain.hammond@gmail.com");
        boolean event4_OK = customerEventHandler.handleEvent(event4);
        customer = customerRepository.findOne(id);
        Assert.assertTrue(event4_OK);
        Assert.assertEquals(email, customer.getEmailAddress());
        Assert.assertEquals(name4, customer.getName());
        Assert.assertEquals(4, customer.getVersion());
        Assert.assertEquals(password, customer.getPassword());
        Assert.assertEquals(dob, customer.getDob());


    }
}
