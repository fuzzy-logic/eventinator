package com.netaporter.eventinator.test.customer.events;

import com.netaporter.eventinator.event.AbstractEvent;
import com.netaporter.eventinator.test.customer.model.Customer;

/**
 * User: gawain
 */
public abstract class AbstractCustomerEvent extends AbstractEvent {



    @Override
    public Class getDomainObjectClass() {
        return Customer.class;
    }
}
