package com.thoughtworks.meeting.event;

/**
 * Created by sven on 14-12-26.
 */
public class EventResult extends Event {
    protected long startTime;
    public EventResult(String name,long interval,String intervalString,long startTime){
        super(name,interval,intervalString);
        this.startTime = startTime;
    }
    public long getStartTime(){
        return this.startTime;
    }
}
