package com.thoughtworks.meeting.event;

/**
 * Created by sven on 14-12-26.
 */
public abstract class Controller {
    protected Controller successor;
    protected Event event;
    protected long startTime;
    public Controller(Event event,long startTime){
        this.event = event;
        this.startTime = startTime;
    }
    public void setSuccessor(Controller successor){
        this.successor = successor;
    }
    public abstract boolean process();
}
