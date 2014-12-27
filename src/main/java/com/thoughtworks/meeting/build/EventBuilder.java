package com.thoughtworks.meeting.build;

import com.thoughtworks.meeting.event.Event;
import com.thoughtworks.meeting.event.EventGenerator;
import com.thoughtworks.meeting.event.EventGeneratorImpl;
import com.thoughtworks.meeting.event.EventResult;
import com.thoughtworks.meeting.io.ConfigReader;
import com.thoughtworks.meeting.io.EventReader;
import com.thoughtworks.meeting.parser.ParserFactory;
import com.thoughtworks.meeting.parser.StringProxy;
import com.thoughtworks.meeting.utils.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sven on 14-12-25.
 */
public class EventBuilder {
    public EventBuilder(){
        List<String> eventList = EventReader.getInput("input.txt");
        List<Event> list = new ArrayList<Event>();
        for (int i = 0;i < eventList.size();i++){
            StringProxy sp = new StringProxy(ParserFactory.getInstance().create(eventList.get(i)),eventList.get(i));
            Event event = sp.getEvent(); //new Event(sp.getEvent().getName(),sp.getEvent().getInterval(),sp.getEvent().getIntervalString());
            list.add(event);
        }
        List<List<EventResult>> tracks = new ArrayList<List<EventResult>>();
        //while(list.size() > 0){
        EventGeneratorImpl generator = new EventGeneratorImpl(list);
        List<EventResult> track = generator.getTrack();
        tracks.add(track);
        while (generator.getEventHasNotProcessed().size() > 0){
            generator = new EventGeneratorImpl(generator.getEventHasNotProcessed());
            tracks.add(generator.getTrack());
        }
        //}
        for(int i = 0; i < tracks.size();i++){
            System.out.println("Track " + (i + 1) + ":");
            for(int j = 0;j < tracks.get(i).size();j++){
                EventResult er = tracks.get(i).get(j);
                System.out.println(TimeUtils.getHourAndMin(er.getStartTime()) + " " + er.getName() + " " + er.getIntervalString());
            }
        }
    }
}
