package com.netaporter.eventinator.test.customer.events;

import com.netaporter.eventinator.test.customer.commands.ChangeCustomerNameCommand;

/**
 * User: gawain
 */
public class ChangeCustomerNameEvent extends AbstractCustomerEvent {

      String newName;

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }



    @Override
    public Class getCommandClass() {
        return ChangeCustomerNameCommand.class;
    }




}
