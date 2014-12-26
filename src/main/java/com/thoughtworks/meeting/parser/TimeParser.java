package com.thoughtworks.meeting.parser;

import com.thoughtworks.meeting.event.Event;

/**
 * Created by sven on 14-12-25.
 */
public abstract class TimeParser implements Parser {
    protected String eventName;
    protected long interval;
    protected String intervalString;
    public Parser parse(String info) throws StringParserException{
        return this;
    }

   public Event getEvent(){
       return new Event(this.eventName,this.interval,this.intervalString);
   }
    public String getIntervalString(){
        return this.intervalString;
    }
}
