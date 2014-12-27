package com.thoughtworks.meeting;

import com.thoughtworks.meeting.parser.LightningParser;
import com.thoughtworks.meeting.parser.MinuteParser;
import com.thoughtworks.meeting.parser.StringProxy;
import com.thoughtworks.meeting.event.Event;
import junit.framework.TestCase;

/**
 * Created by sven on 14-12-25.
 */
public class TestStringProxy extends TestCase {
    public void testStringParser(){
        StringProxy sp = new StringProxy(new MinuteParser(),"first 11min");
        assertNotNull(sp.getEvent().getName());
        StringProxy sp2 = new StringProxy(new LightningParser(),"ads lightning");
        assertNotNull(sp2.getEvent().getName());
    }
}
