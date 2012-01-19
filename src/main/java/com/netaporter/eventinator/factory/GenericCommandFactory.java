package com.netaporter.eventinator.factory;

import com.netaporter.eventinator.command.AbstractCommand;
import com.netaporter.eventinator.command.Command;
import com.netaporter.eventinator.domain.VersionIncrementor;
import com.netaporter.eventinator.event.Event;

import java.lang.reflect.Constructor;

/**
 * User: gawain
 */
public class GenericCommandFactory implements  CommandFactory {


    String versionField;

    @Override
     public Command createCommand(Event event, Object domainObject) {
        Command command = getCommand(event, domainObject);
        return command;
    }

    private Command getCommand(Event event, Object domainObject) {
        Class aClass = event.getCommandClass();
        try {
            Constructor constructor = aClass.getConstructors()[0];
            Command command = (Command) constructor.newInstance(event, domainObject);
            return command;
        } catch (Exception e) {
            throw new RuntimeException("Cannot use reflection to create command " +
                    aClass + " with event: " + event.getCommandClass().getSimpleName() + " and domainObject: " + domainObject +
                    ". Check the event's getCommandClass() method has been implemented with the right Command class", e);
        }
    }

    public void setVersionField(String versionField) {
        this.versionField = versionField;
    }
}
