package com.thoughtworks.meeting;

import com.thoughtworks.meeting.io.EventReader;
import junit.framework.TestCase;

/**
 * Created by sven on 14-12-25.
 */
public class TestEventReader extends TestCase {
    public void testEventReader(){
        //assertNotNull(EventReader.getInput("input.txt"));
        assertTrue(EventReader.getInput("input.txt").size() > 0);
    }
}
