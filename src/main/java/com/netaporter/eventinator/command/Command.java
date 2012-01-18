package com.netaporter.eventinator.command;

import com.netaporter.customerservice.customer.Customer;

/**
 * User: gawain
 */
public interface Command<T> {


    public T apply() ;

    /**
     * Could have an undo option
     */
    // public T undo() ;
}
