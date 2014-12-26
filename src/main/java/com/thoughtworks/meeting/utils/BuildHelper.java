package com.thoughtworks.meeting.utils;

import java.util.List;
import com.thoughtworks.meeting.event.Event;

/**
 * Created by sven on 14-12-27.
 */
public class BuildHelper {
    public static boolean hasFitEventInList(List<Event> eventList,long interval){
        for(Event event : eventList){
            if(event.getInterval() <= interval){
                return true;
            }
        }
        return false;
    }
}
