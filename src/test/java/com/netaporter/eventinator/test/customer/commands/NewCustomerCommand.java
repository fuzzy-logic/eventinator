package com.netaporter.eventinator.test.customer.commands;

import com.netaporter.eventinator.command.AbstractCommand;
import com.netaporter.eventinator.test.customer.builder.CustomerBuilder;
import com.netaporter.eventinator.test.customer.events.NewCustomerEvent;
import com.netaporter.eventinator.test.customer.model.Customer;

/**
 * User: gawain
 */
public class NewCustomerCommand extends AbstractCommand<NewCustomerEvent, Customer> {

/*    NewCustomerCommand() {
        super();
    }*/

    public NewCustomerCommand(NewCustomerEvent event, Customer domainObject) {
        super(event, domainObject);
    }

    @Override
    protected Customer applyCommand(NewCustomerEvent event, Customer domainObject) {
        String name = event.getNewName();
        String password =   event.getNewPassword();
        String email = event.getNewEmail();
        CustomerBuilder builder = CustomerBuilder.create(name, password, email);
        builder.setDob(event.getNewDob());
        builder.setId(event.getDomainObjectId().toString());
        Customer customer = builder.build();
        return customer;
    }

/*    @Override
    public Command create(NewCustomerEvent event, Customer domainObject) {
        return new NewCustomerCommand(event, domainObject);
    }*/


}


