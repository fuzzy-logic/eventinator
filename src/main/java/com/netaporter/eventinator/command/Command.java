package com.netaporter.eventinator.command;


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
