package com.netaporter.eventinator.test.customer.events;

import com.netaporter.eventinator.test.customer.commands.NewCustomerCommand;

/**
 * User: gawain
 */
public class NewCustomerEvent extends AbstractCustomerEvent {


     String newName;
    String newEmail;
    String newDob;
    String newPassword;

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getNewDob() {
        return newDob;
    }

    public void setNewDob(String newDob) {
        this.newDob = newDob;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }



    @Override
    public Class getCommandClass() {
        return NewCustomerCommand.class;
    }


}
