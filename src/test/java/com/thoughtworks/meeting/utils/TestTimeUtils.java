package com.thoughtworks.meeting.utils;

import com.thoughtworks.meeting.utils.TimeUtils;
import junit.framework.TestCase;

/**
 * Created by sven on 14-12-27.
 */
public class TestTimeUtils extends TestCase {
    public void testTimeUtils(){
        long intervalTime = TimeUtils.getIntervalTimeByHours(9);
        assertEquals(9 * 60 * 60 * 1000,intervalTime);
        long intervalTime2 = TimeUtils.getIntervalTimeByMinutes(30);
        assertEquals(30 * 60 * 1000,intervalTime2);
    }
}
