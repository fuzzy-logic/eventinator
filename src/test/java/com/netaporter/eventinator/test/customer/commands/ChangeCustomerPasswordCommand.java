package com.netaporter.eventinator.test.customer.commands;

import com.netaporter.eventinator.command.AbstractCommand;
import com.netaporter.eventinator.test.customer.builder.CustomerBuilder;
import com.netaporter.eventinator.test.customer.events.ChangeCustomerPasswordEvent;
import com.netaporter.eventinator.test.customer.model.Customer;

/**
 * User: gawain
 */
public class ChangeCustomerPasswordCommand  extends AbstractCommand<ChangeCustomerPasswordEvent, Customer> {


/*    public ChangeCustomerPasswordCommand() {
        super();
    }*/

    public ChangeCustomerPasswordCommand(ChangeCustomerPasswordEvent event, Customer domainObject) {
        super(event, domainObject);
    }

    @Override
    protected Customer applyCommand(ChangeCustomerPasswordEvent event, Customer domainObject) {
        CustomerBuilder builder =   CustomerBuilder.create(domainObject);
        builder.setPassword(event.getNewPassword());
        return  builder.build();
    }

/*    @Override
    public Command create(ChangeCustomerPasswordEvent event, Customer domainObject) {
        return new ChangeCustomerPasswordCommand(event, domainObject);
    }*/


}