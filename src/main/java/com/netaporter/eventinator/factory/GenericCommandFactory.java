package com.netaporter.eventinator.factory;

import com.netaporter.eventinator.command.Command;
import com.netaporter.eventinator.event.Event;
import com.netaporter.eventinator.model.DomainObject;

import java.lang.reflect.Constructor;

/**
 * User: gawain
 */
public class GenericCommandFactory implements  CommandFactory {




    @Override
     public Command createCommand(Event event, DomainObject domainObject) {
        Command command = getCommand(event, domainObject);
        return command;
    }

    private Command getCommand(Event event, DomainObject domainObject) {
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


/*    public void setCommandMap(Map<Event, AbstractCommand> commandMap) {
        this.commandMap = new HashMap<Class, AbstractCommand>();
        for (Map.Entry<Event, AbstractCommand> entry : commandMap.entrySet()) {
            Class classType = entry.getKey().getClass();
             this.commandMap.put(classType, entry.getValue());
        }
    }*/
}
