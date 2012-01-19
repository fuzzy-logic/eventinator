package com.netaporter.eventinator.service;

import com.netaporter.eventinator.repos.DomainRepository;
import com.netaporter.eventinator.test.customer.events.DeleteCustomerEvent;
import com.netaporter.eventinator.factory.GenericCommandFactory;
import com.netaporter.eventinator.repos.DefaultDomainRepository;
import com.netaporter.eventinator.repos.EventRepository;
import com.netaporter.eventinator.test.customer.builder.CustomerBuilder;
import com.netaporter.eventinator.test.customer.events.ChangeCustomerEmailEvent;
import com.netaporter.eventinator.test.customer.events.ChangeCustomerNameEvent;
import com.netaporter.eventinator.test.customer.events.NewCustomerEvent;
import com.netaporter.eventinator.test.customer.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
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
    CrudRepository customerRepository;
    StubDomainRepository domainRepository;
    EventRepository eventRepository;
    Customer persistedCustomer;
    Customer newCustomer;
    String currentId = "123";

    @Before
    public void setup() {
        persistedCustomer = CustomerBuilder.create("name", "password", "email").setId(currentId).build();
        eventHandler = new GenericEventProcessor();
        customerRepository = mock(CrudRepository.class);
        eventRepository = mock(EventRepository.class);
        commandFactory = new GenericCommandFactory();
        domainRepository = new StubDomainRepository();


        eventHandler.setCommandFactory(commandFactory);
        eventHandler.setDomainObjectRepository(domainRepository);
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
        assertTrue(domainRepository.deleteCalled);
        assertNotSame(newCustomer, persistedCustomer);
        assertNull(newCustomer);
        assertEquals(persistedCustomer.getId(), domainRepository.deletedId);
        assertEquals(persistedCustomer.getClass(), domainRepository.deletedClass);

    }



    public class StubDomainRepository implements DomainRepository {

        public boolean deleteCalled = false;
        public Class deletedClass;
        public Serializable deletedId;

        @Override
        public Object findOne(Class aClass, Serializable id) {
            return persistedCustomer;
        }

        @Override
        public void delete(Class aClass, Serializable id) {
            deleteCalled = true;
            deletedClass  = aClass;
            deletedId = id;

        }

        @Override
        public void save(Object object) {
            newCustomer = (Customer) object;
        }
    }
}
