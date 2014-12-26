package com.thoughtworks.meeting.event;

import java.util.Date;

/**
 * Created by sven on 14-12-24.
 */
public class Event {
    protected String name;
    protected long interval;
    protected String intervalString;

    public Event(String name,long interval,String intervalString){
        this.name = name;
        this.interval = interval;
        this.intervalString = intervalString;
    }
    public boolean canBeAdded(){
        return true;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    // not to supply method to change the end time
    /*public void setEndTime(Date endTime){
        this.endTime = endTime;
    }*/
    public long getInterval(){
        return this.interval;
    }
    public String getIntervalString(){
        return this.intervalString;
    }
}
