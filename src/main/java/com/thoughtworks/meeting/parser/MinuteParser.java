package com.thoughtworks.meeting.parser;

import com.thoughtworks.meeting.utils.TimeUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sven on 14-12-25.
 */
public class MinuteParser extends TimeParser {
    public Parser parse(String info) throws StringParserException{
        Pattern patten = Pattern.compile("^([A-Za-z0-9_-]\\s*)*\\s.*[0-9]{1,2}[a-z]{3}$");
        Pattern timePatten = Pattern.compile("[0-9]{1,2}[a-z]{3}");
        Matcher matcher = patten.matcher(info);
        Matcher timeMatcher = timePatten.matcher(info);
        String time = "";
        if (!matcher.matches())
            throw new StringParserException("The string input of '" + info + "' is invalid!");
        while (timeMatcher.find())
            time = timeMatcher.group();

        //System.out.println(time);
        this.eventName = info.substring(0,info.indexOf(time));
        String timeUnit = time.substring(time.length() - 3,time.length());
        this.interval = TimeUtils.getIntervalTimeByMinutes(Integer.parseInt(time.substring(0,time.length() - 3)));
        this.intervalString = info.substring(info.indexOf(time));
        if (!timeUnit.equals("min"))
            throw new StringParserException("The unit must be 'min',got '" + timeUnit + "'");
        //System.out.println("time unit :" + timeUnit);
        //System.out.println("time interval :" + timeInterval);
        return this;
    }
}
