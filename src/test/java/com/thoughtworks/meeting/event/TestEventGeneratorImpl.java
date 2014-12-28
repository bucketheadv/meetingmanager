package com.thoughtworks.meeting.event;

import com.thoughtworks.meeting.event.Event;
import com.thoughtworks.meeting.event.EventGeneratorImpl;
import com.thoughtworks.meeting.event.EventResult;
import com.thoughtworks.meeting.io.EventReader;
import com.thoughtworks.meeting.parser.ParserFactory;
import com.thoughtworks.meeting.parser.StringProxy;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sven on 14-12-27.
 */
public class TestEventGeneratorImpl extends TestCase{
    public void testEventGeneratorImpl(){
        List<String> eventList = EventReader.getInput("input.txt");
        List<Event> list = new ArrayList<Event>();
        for (int i = 0;i < eventList.size();i++){
            StringProxy sp = new StringProxy(ParserFactory.getInstance().create(eventList.get(i)),eventList.get(i));
            Event event = sp.getEvent(); //new Event(sp.getEvent().getName(),sp.getEvent().getInterval(),sp.getEvent().getIntervalString());
            list.add(event);
        }
        List<List<EventResult>> tracks = new ArrayList<List<EventResult>>();
        EventGeneratorImpl generator = new EventGeneratorImpl(list);
        assertNotNull(generator.getTrack());
        assertTrue(generator.getTrack().size() > 0);
    }
}
