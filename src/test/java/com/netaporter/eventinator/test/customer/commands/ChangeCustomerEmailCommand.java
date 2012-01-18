package com.netaporter.eventinator.test.customer.commands;

import com.netaporter.eventinator.command.AbstractCommand;
import com.netaporter.eventinator.test.customer.builder.CustomerBuilder;
import com.netaporter.eventinator.test.customer.events.ChangeCustomerEmailEvent;
import com.netaporter.eventinator.test.customer.model.Customer;

/**
 * User: gawain
 */
public class ChangeCustomerEmailCommand extends AbstractCommand<ChangeCustomerEmailEvent, Customer> {


    public ChangeCustomerEmailCommand(ChangeCustomerEmailEvent event, Customer domainObject) {
        super(event, domainObject);
    }

    @Override
    protected Customer applyCommand(ChangeCustomerEmailEvent event, Customer domainObject) {
        CustomerBuilder builder = CustomerBuilder.create(domainObject);
        builder.setEmail(event.getNewEmail());
        return  builder.build();
    }
}
