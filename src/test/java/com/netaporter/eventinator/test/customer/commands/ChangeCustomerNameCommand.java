package com.netaporter.eventinator.test.customer.commands;

import com.netaporter.eventinator.command.AbstractCommand;
import com.netaporter.eventinator.test.customer.builder.CustomerBuilder;
import com.netaporter.eventinator.test.customer.events.ChangeCustomerNameEvent;
import com.netaporter.eventinator.test.customer.model.Customer;

/**
 * User: gawain
 */
public class ChangeCustomerNameCommand extends AbstractCommand<ChangeCustomerNameEvent, Customer> {

/*    ChangeCustomerNameCommand() {
        super();
    }*/

    public ChangeCustomerNameCommand(ChangeCustomerNameEvent event, Customer domainObject) {
        super(event, domainObject);
    }



    @Override
    protected Customer applyCommand(ChangeCustomerNameEvent event, Customer customer) {
        CustomerBuilder builder = CustomerBuilder.create(customer);
        builder.setName(event.getNewName());
        return builder.build();
    }

/*    @Override
    public Command create(ChangeCustomerNameEvent event, Customer domainObject) {
        return new ChangeCustomerNameCommand(event, domainObject);
    }*/


}
