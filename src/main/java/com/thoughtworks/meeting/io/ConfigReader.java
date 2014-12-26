package com.thoughtworks.meeting.io;

import com.thoughtworks.meeting.utils.PathUtils;

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by sven on 14-12-24.
 */
public class ConfigReader {
    public static ConfigReader instance = null;
    private HashMap<String,String> configs = null;
    public synchronized static ConfigReader getInstance(){
        if (instance == null){
            instance = new ConfigReader();
        }
        return instance;
    }
    public ConfigReader(){
        Properties prop = new Properties();
        System.out.println("current path: " + System.getProperty("user.dir") + "/src/main/java/");
        configs = new HashMap<String, String>();
        try {
            prop.load(new FileInputStream(PathUtils.getPath("config.properties")));
            }catch (Exception e) {
            e.printStackTrace();
        }
        Enumeration enum_r = prop.propertyNames();
        while(enum_r.hasMoreElements()){
            String key = (String)enum_r.nextElement();
            String val = prop.getProperty(key);
            configs.put(key,val);
        }
    }
    public String get(String key){
        return configs.get(key);
    }
    public int getEventStartTime(){
        return Integer.parseInt(get("start_time_hour"));
    }
    public int getEventEndTime(){
        return Integer.parseInt(get("end_time_hour"));
    }
    public int getLunchStartTime(){
        return Integer.parseInt(get("lunch_start_hour"));
    }
    public int getLunchDuration(){
        return Integer.parseInt(get("lunch_duration"));
    }
}
