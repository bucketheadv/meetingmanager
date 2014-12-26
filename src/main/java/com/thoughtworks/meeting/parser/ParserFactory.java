package com.thoughtworks.meeting.parser;

/**
 * Created by sven on 14-12-25.
 */
public class ParserFactory {
    private static ParserFactory instance;
    public synchronized static ParserFactory getInstance(){
        if (null == instance){
            instance = new ParserFactory();
        }
        return instance;
    }
    public Parser create(String eventLine){
        if (eventLine.endsWith("min")){
            return new MinuteParser();
        }else if(eventLine.endsWith("lightning")){
            return new LightningParser();
        }
        return null;
    }
}
