package com.netaporter.eventinator.test.customer.events;

import com.netaporter.eventinator.test.customer.commands.DeleteCustomerCommand;

/**
 * User: gawain
 */
public class DeleteCustomerEvent extends AbstractCustomerEvent {

    @Override
    public Class getCommandClass() {
        return DeleteCustomerCommand.class;
    }

}
