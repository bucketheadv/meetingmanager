package com.thoughtworks.meeting.parser;

import com.thoughtworks.meeting.event.Event;

/**
 * Created by sven on 14-12-25.
 */
public interface Parser {
    public Parser parse(String info) throws StringParserException;
    public Event getEvent();
    public String getIntervalString();
}
