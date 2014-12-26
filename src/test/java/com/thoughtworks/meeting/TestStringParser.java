package com.thoughtworks.meeting;

import com.thoughtworks.meeting.parser.MinuteParser;
import com.thoughtworks.meeting.parser.StringProxy;
import junit.framework.TestCase;

/**
 * Created by sven on 14-12-25.
 */
public class TestStringParser extends TestCase {
    public void testStringParser(){
        StringProxy sp = new StringProxy(new MinuteParser(),"");
    }
}
