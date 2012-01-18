package com.netaporter.eventinator.test.customer.events;

import com.netaporter.eventinator.test.customer.commands.ChangeCustomerPasswordCommand;

/**
 * User: gawain
 */
public class ChangeCustomerPasswordEvent extends AbstractCustomerEvent {

    String newPassword;



    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public Class getCommandClass() {
        return ChangeCustomerPasswordCommand.class;
    }

}
