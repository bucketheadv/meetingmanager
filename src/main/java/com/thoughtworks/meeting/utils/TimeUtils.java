package com.thoughtworks.meeting.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
        String timeStr = "1970/01/01 " + hour + ":" + "00:00";
        return formatDate(timeStr).getTime();
    }
    public static long getEventTime(int hour,int min){
        String timeStr = "1970/01/01 " + hour + ":" + min + ":00";
        return formatDate(timeStr).getTime();
    }
    public static String getHourAndMin(long time){
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ssa", Locale.ENGLISH);
        return sdf.format(date);
    }
    private static Date formatDate(String timeStr){
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/DD hh:mm:ss");
        try {
            date = sdf.parse(timeStr);
        }catch (Exception e){
            e.printStackTrace();
        }
        return date;
    }

}
