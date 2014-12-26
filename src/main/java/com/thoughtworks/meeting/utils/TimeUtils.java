package com.thoughtworks.meeting.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 * Created by sven on 14-12-25.
 */
public class TimeUtils {
    public static long getIntervalTimeByMinutes(int min){
        return min * 60 * 1000;
    }
    public static long getIntervalTimeByHours(int hour){
        return hour * 60 * 60 * 1000;
    }
    public static long getEventTime(int hour){
        String moOrAf = "AM";
        if(hour == 12){
            moOrAf = "PM";
        }
        String timeStr = "1970/01/01 " + hour + ":" + "00:00" + moOrAf;
        return formatDate(timeStr).getTime();
    }
    public static long getEventTime(int hour,int min){
        String moOrAf = "AM";
        if(hour == 12){
            moOrAf = "PM";
        }
        String timeStr = "1970/01/01 " + hour + ":" + min + ":00" + moOrAf;
        return formatDate(timeStr).getTime();
    }
    public static String getHourAndMin(long time){
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mma", Locale.ENGLISH);
 //       sdf.setTimeZone(new SimpleTimeZone(28800000, "GMT+8"));
        return sdf.format(date);
    }
    private static Date formatDate(String timeStr){
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss",Locale.ENGLISH);
//        sdf.setTimeZone(new SimpleTimeZone(28800000, "GMT+8"));
        try {
            date = sdf.parse(timeStr);
        }catch (Exception e){
            e.printStackTrace();
        }
        //System.out.println("time str = " + timeStr);
        //System.out.println("revert time = " + getHourAndMin(date.getTime()));
        return date;
    }

}
