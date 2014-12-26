package com.thoughtworks.meeting.event;

import com.thoughtworks.meeting.io.ConfigReader;
import com.thoughtworks.meeting.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * Created by sven on 14-12-24.
 */
public class EventGeneratorImpl implements EventGenerator {
    private List<EventResult> track;
    private List<Event> eventList;
    private ConfigReader configReader = null;
    public EventGeneratorImpl(List<Event> eventList){
        this.track = new ArrayList<EventResult>();
        this.eventList = eventList;
        configReader = ConfigReader.getInstance();
    }

    @Override
    public void generateMorningEvent() {
        long startTime = TimeUtils.getEventTime(configReader.getEventStartTime());// 1970/01/01 09:00:00

    }

    @Override
    public void generateLunchEvent() {
        EventResult er = new EventResult("Lunch",TimeUtils.getIntervalTimeByMinutes(configReader.getLunchDuration()),TimeUtils.getEventTime(configReader.getLunchStartTime()));
        this.track.add(er);
    }

    @Override
    public void generateAfternoonEvent() {

    }

    @Override
    public void generateEveningEvent() {

    }

    @Override
    public List<EventResult> getTrack() {
        this.generateMorningEvent();
        this.generateLunchEvent();
        this.generateAfternoonEvent();
        this.generateEveningEvent();
        return this.track;
    }
}
