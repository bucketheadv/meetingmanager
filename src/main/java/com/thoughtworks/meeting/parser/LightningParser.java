package com.thoughtworks.meeting.parser;

import com.thoughtworks.meeting.utils.TimeUtils;

/**
 * Created by sven on 14-12-25.
 */
public class LightningParser extends TimeParser{
    @Override
    public Parser parse(String info) throws StringParserException {
        String lightning = "lightning";
        if(!info.endsWith(lightning))
            throw new StringParserException("Not lightning time event");

        this.eventName = info.substring(0,info.indexOf(lightning));
        this.interval = TimeUtils.getIntervalTimeByMinutes(5);
        this.intervalString = "lighting";
        return this;
    }
}
