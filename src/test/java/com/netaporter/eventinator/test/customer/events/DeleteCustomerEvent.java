package com.netaporter.eventinator.test.customer.events;

import com.netaporter.eventinator.command.DeleteObjectCommand;

/**
 * User: gawain
 */
public class DeleteCustomerEvent extends AbstractCustomerEvent {

    @Override
    public Class getCommandClass() {
        return DeleteObjectCommand.class;
    }

}
