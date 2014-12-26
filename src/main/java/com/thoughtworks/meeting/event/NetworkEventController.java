package com.thoughtworks.meeting.event;

import com.thoughtworks.meeting.utils.TimeUtils;

/**
 * Created by sven on 14-12-26.
 */
public class NetworkEventController extends Controller{
    public NetworkEventController(Event event,long startTime){
        super(event,startTime);
    }
    public boolean process(){
        if (!this.event.getName().contains("Networking event")){
            return false;
        }
        long networkEventStartTimeNotBefore = TimeUtils.getEventTime(16);
        long networkEventStartTimeNotAfter = TimeUtils.getEventTime(17);
        if (this.startTime < networkEventStartTimeNotBefore || this.startTime > networkEventStartTimeNotAfter)
            return false;
        if (this.successor != null)
            return this.successor.process();
        else
            return true;
    }
}
