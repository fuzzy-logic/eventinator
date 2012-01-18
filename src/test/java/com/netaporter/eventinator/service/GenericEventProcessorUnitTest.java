package com.netaporter.eventinator.service;

import com.netaporter.eventinator.test.customer.events.DeleteCustomerEvent;
import com.netaporter.eventinator.factory.GenericCommandFactory;
import com.netaporter.eventinator.repos.DomainRepository;
import com.netaporter.eventinator.repos.EventRepository;
import com.netaporter.eventinator.test.customer.builder.CustomerBuilder;
import com.netaporter.eventinator.test.customer.events.ChangeCustomerEmailEvent;
import com.netaporter.eventinator.test.customer.events.ChangeCustomerNameEvent;
import com.netaporter.eventinator.test.customer.events.NewCustomerEvent;
import com.netaporter.eventinator.test.customer.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.repository.CrudRepository;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.*;

import java.io.Serializable;

/**
 * User: gawain
 */
public class GenericEventProcessorUnitTest {

    GenericEventProcessor eventHandler;
    GenericCommandFactory commandFactory;
    StubRepository customerRepository;
    DomainRepository commonRepository;
    EventRepository eventRepository;
    Customer persistedCustomer;
    Customer newCustomer;
    String currentId = "123";

    @Before
    public void setup() {
        persistedCustomer = CustomerBuilder.create("name", "password", "email").setId(currentId).build();
        eventHandler = new GenericEventProcessor();
        customerRepository = new StubRepository();
        eventRepository = mock(EventRepository.class);
        commandFactory = new GenericCommandFactory();

        eventHandler.setCommandFactory(commandFactory);
        eventHandler.setDomainObjectRepository(commonRepository);
        eventHandler.setEventRepository(eventRepository);

    }

    @Test
    public void testHandleNewCustomerEvent() {

        String newName = "Jim";
        String newDob = "1/6/1983";
        String newEmail = "jim@gmail.com";
        String newPassword = "changeme";
        String newId = "123";

        NewCustomerEvent newCustomerEvent = new NewCustomerEvent();
        newCustomerEvent.setNewName(newName);
        newCustomerEvent.setNewDob(newDob);
        newCustomerEvent.setNewEmail(newEmail);
        newCustomerEvent.setNewPassword(newPassword);
        newCustomerEvent.setDomainObjectId(newId);


        boolean eventStatus = eventHandler.handleEvent(newCustomerEvent);

        System.out.println();

        assertTrue(eventStatus);
        verify(eventRepository).save(newCustomerEvent);
        assertNotSame(newCustomer, persistedCustomer);
        assertEquals(newId, newCustomer.getId());
        assertEquals(newName, newCustomer.getName());
        assertEquals(newEmail, newCustomer.getEmailAddress());
        assertEquals(newPassword, newCustomer.getPassword());


    }


    @Test
    public void testHandleChangeCustomerNameEvent() {

        String newName = "Jim";


        ChangeCustomerNameEvent changeCustomerEvent = new ChangeCustomerNameEvent();
        changeCustomerEvent.setNewName(newName);
        changeCustomerEvent.setDomainObjectId(currentId);


        boolean eventStatus = eventHandler.handleEvent(changeCustomerEvent);

        System.out.println();

        assertTrue(eventStatus);
        verify(eventRepository).save(changeCustomerEvent);
        assertNotSame(newCustomer, persistedCustomer);
        assertEquals(persistedCustomer.getId(), newCustomer.getId());
        assertEquals(newName, newCustomer.getName());
        assertEquals(persistedCustomer.getEmailAddress(), newCustomer.getEmailAddress());
        assertEquals(persistedCustomer.getPassword(), newCustomer.getPassword());


    }

    @Test
    public void testHandleChangeCustomerEmailEvent() {
          String newEmail = "Jim@tonydance.com";


        ChangeCustomerEmailEvent changeCustomerEvent = new ChangeCustomerEmailEvent();
        changeCustomerEvent.setNewEmail(newEmail);
        changeCustomerEvent.setDomainObjectId(currentId);


        boolean eventStatus = eventHandler.handleEvent(changeCustomerEvent);

        System.out.println();

        assertTrue(eventStatus);
        verify(eventRepository).save(changeCustomerEvent);
        assertNotSame(newCustomer, persistedCustomer);
        assertEquals(persistedCustomer.getId(), newCustomer.getId());
        assertEquals(persistedCustomer.getName(), newCustomer.getName());
        assertEquals(newEmail, newCustomer.getEmailAddress());
        assertEquals(persistedCustomer.getPassword(), newCustomer.getPassword());
    }

    @Test
    public void testHandleDeleteCustomerEmailEvent() {


        DeleteCustomerEvent changeCustomerEvent = new DeleteCustomerEvent();
        changeCustomerEvent.setDomainObjectId(currentId);


        boolean eventStatus = eventHandler.handleEvent(changeCustomerEvent);

        System.out.println();

        assertTrue(eventStatus);
        verify(eventRepository).save(changeCustomerEvent);
        assertNotSame(newCustomer, persistedCustomer);
        assertEquals(persistedCustomer.getId(), newCustomer.getId());
        assertEquals(persistedCustomer.getName(), newCustomer.getName());
        assertEquals(persistedCustomer.getEmailAddress(), newCustomer.getEmailAddress());
        assertEquals(persistedCustomer.getPassword(), newCustomer.getPassword());
        assertTrue(newCustomer.isDeleted());
    }

    public class StubRepository implements CrudRepository<Customer, Serializable> {

        public boolean savedCalled = false;

        public Customer save(Customer customer) {
            newCustomer = customer;
            return customer;
        }

        public Customer findOne(Serializable serializable) {
            return persistedCustomer;
        }

        public Iterable<Customer> save(Iterable<? extends Customer> iterable) { this.savedCalled = true;   return null;  }
        public boolean exists(Serializable serializable) {   return false;  }
        public Iterable<Customer> findAll() {  return null;  }
        public long count() {  return 0; }
        public void delete(Serializable serializable) { }
        public Customer findOne(String s) {  return null; }
        public boolean exists(String s) {  return false;  }
        public void delete(String s) {  }
        public void delete(Customer customer) {  }
        public void delete(Iterable<? extends Customer> iterable) {   }
        public void deleteAll() {   }


    }
}
