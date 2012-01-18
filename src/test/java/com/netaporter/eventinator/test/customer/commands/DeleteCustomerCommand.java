package com.netaporter.eventinator.test.customer.commands;

import com.netaporter.eventinator.command.AbstractCommand;
import com.netaporter.eventinator.test.customer.builder.CustomerBuilder;
import com.netaporter.eventinator.test.customer.events.DeleteCustomerEvent;
import com.netaporter.eventinator.test.customer.model.Customer;

/**
 * User: gawain
 */
public class DeleteCustomerCommand extends AbstractCommand<DeleteCustomerEvent, Customer> {
    public DeleteCustomerCommand(DeleteCustomerEvent event, Customer domainObject) {
        super(event, domainObject);
    }

    @Override
    protected Customer applyCommand(DeleteCustomerEvent event, Customer domainObject) {
        CustomerBuilder builder = CustomerBuilder.create(domainObject);
        builder.setDeleted(true);
        return builder.build();
    }
}
