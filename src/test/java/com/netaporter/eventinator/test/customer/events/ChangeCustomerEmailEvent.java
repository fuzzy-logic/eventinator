package com.netaporter.eventinator.test.customer.events;

import com.netaporter.eventinator.test.customer.commands.ChangeCustomerEmailCommand;

/**
 * User: gawain
 */
public class ChangeCustomerEmailEvent extends AbstractCustomerEvent {

    String newEmail;



    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }



    @Override
    public Class getCommandClass() {
        return ChangeCustomerEmailCommand.class;
    }

}
