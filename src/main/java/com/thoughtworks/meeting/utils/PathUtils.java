package com.thoughtworks.meeting.utils;

/**
 * Created by sven on 14-12-25.
 */
public class PathUtils {
    public static String getPath(String filename){
        return System.getProperty("user.dir") + "/src/main/java/" + filename;
    }
}
