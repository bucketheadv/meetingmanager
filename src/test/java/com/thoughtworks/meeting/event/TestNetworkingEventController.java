package com.thoughtworks.meeting.event;

import com.thoughtworks.meeting.event.Controller;
import com.thoughtworks.meeting.event.NetworkEventController;
import com.thoughtworks.meeting.utils.TimeUtils;
import junit.framework.TestCase;
import com.thoughtworks.meeting.event.Event;

/**
 * Created by sven on 14-12-27.
 */
public class TestNetworkingEventController extends TestCase {
    public void testTestNetworkingEventController(){
        Event event = new Event("Networking event",TimeUtils.getIntervalTimeByMinutes(30),"30min");
        Controller controller = new NetworkEventController(event, TimeUtils.getEventTime(9));
        assertFalse(controller.process());
        Event event1 = new Event("Networking ev",TimeUtils.getIntervalTimeByMinutes(30),"30min");
        Controller controller1 = new NetworkEventController(event1,TimeUtils.getEventTime(16));
        assertFalse(controller1.process());
        Event event2 = new Event("Networking event 10min",TimeUtils.getIntervalTimeByMinutes(10),"10min");
        Controller controller2 = new NetworkEventController(event2,TimeUtils.getEventTime(16));
        assertTrue(controller2.process());
    }
}
