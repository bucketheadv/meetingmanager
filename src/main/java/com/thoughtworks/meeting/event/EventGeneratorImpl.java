package com.thoughtworks.meeting.event;

import com.thoughtworks.meeting.io.ConfigReader;
import com.thoughtworks.meeting.utils.BuildHelper;
import com.thoughtworks.meeting.utils.RandomUtils;
import com.thoughtworks.meeting.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sven on 14-12-24.
 */
public class EventGeneratorImpl implements EventGenerator {
    private List<EventResult> track;
    private List<Event> eventList;
    private List<Event> eventHasNotProcessed;
    private ConfigReader configReader = null;
    private long nextStartTime = 0;
    public EventGeneratorImpl(List<Event> eventList){
        this.track = new ArrayList<EventResult>();
        this.eventList = eventList;
        this.eventHasNotProcessed = new ArrayList<Event>(eventList);
        configReader = ConfigReader.getInstance();
    }

    @Override
    public void generateMorningEvent() {
        long startTime = TimeUtils.getEventTime(9);// 1970/01/01 09:00:00
        long timeUntil = TimeUtils.getEventTime(12);
        this.nextStartTime = startTime;
        List<Event> tempList = new ArrayList<Event>(this.eventList);
        while (nextStartTime < timeUntil){
            if(!BuildHelper.hasFitEventInList(tempList,timeUntil - nextStartTime)){
                tempList = new ArrayList<Event>(this.eventList);
                nextStartTime = startTime;
                this.track = new ArrayList<EventResult>();
            }
            if(BuildHelper.allIntervalTime(tempList) < (timeUntil - nextStartTime)){ //to avoid the dead loop
                break;
            }
            int random = RandomUtils.random(tempList.size());
            Event event = tempList.get(random);
            Controller controller = new NetworkEventController(event,nextStartTime);
            if (event.getInterval() + nextStartTime <= timeUntil && !controller.process()){
                EventResult er = new EventResult(event.getName(),event.getInterval(),event.getIntervalString(),nextStartTime);
                this.track.add(er);
                tempList.remove(random);
                nextStartTime = nextStartTime + event.getInterval();
            }
        }
        this.eventHasNotProcessed = tempList;
    }

    @Override
    public void generateLunchEvent() {
        EventResult er = new EventResult("Lunch",TimeUtils.getIntervalTimeByMinutes(configReader.getLunchDuration()),"",TimeUtils.getEventTime(configReader.getLunchStartTime()));
        this.track.add(er);
        this.nextStartTime += TimeUtils.getIntervalTimeByMinutes(configReader.getLunchDuration());
    }

    @Override
    public void generateAfternoonEvent() {
        List<Event> tempList = new ArrayList<Event>(this.eventHasNotProcessed);
        //System.out.println("Afternoon event start at : " + TimeUtils.getHourAndMin(this.nextStartTime));
        //long startTime = this.nextStartTime;
        long endTimeMin = TimeUtils.getEventTime(16);
        long endTimeMax = TimeUtils.getEventTime(17);
        while (this.nextStartTime < endTimeMax){
            if(!BuildHelper.hasFitEventInList(tempList,endTimeMax - this.nextStartTime) && this.nextStartTime > endTimeMin){
                break;
            }
            int random = RandomUtils.random(tempList.size());
            Event event = tempList.get(random);
            Controller controller = new NetworkEventController(event,this.nextStartTime);
            if (this.nextStartTime + event.getInterval() < endTimeMax && !controller.process()){
                EventResult er = new EventResult(event.getName(),event.getInterval(),event.getIntervalString(),this.nextStartTime);
                this.track.add(er);
                tempList.remove(random);
                this.nextStartTime += event.getInterval();
            }
        }
        this.eventHasNotProcessed = new ArrayList<Event>(tempList);
    }

    @Override
    public void generateEveningEvent() {
        Event event = new Event("Networking event",0,"");
        Controller controller = new NetworkEventController(event,this.nextStartTime);
        if (controller.process()){
            EventResult er = new EventResult("Networking event",0,"",this.nextStartTime);
            this.track.add(er);
        }
    }

    @Override
    public List<EventResult> getTrack() {
        this.generateMorningEvent();
        this.generateLunchEvent();
        this.generateAfternoonEvent();
        this.generateEveningEvent();
        return this.track;
    }

    public List<Event> getEventHasNotProcessed(){
        return this.eventHasNotProcessed;
    }
}
