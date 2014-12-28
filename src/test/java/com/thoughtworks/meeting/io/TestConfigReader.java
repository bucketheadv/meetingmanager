package com.thoughtworks.meeting.io;

import com.thoughtworks.meeting.io.ConfigReader;
import junit.framework.TestCase;

/**
 * Created by sven on 14-12-24.
 */
public class TestConfigReader extends TestCase{
    public void testGetKeyOfStartTime(){
        ConfigReader cr = ConfigReader.getInstance();
        String valOfStartTime = cr.get("start_time_hour");
        String valOfEndTime = cr.get("end_time_hour");
        assertEquals("9",valOfStartTime);
        assertEquals("17",valOfEndTime);
    }
}
