package com.thoughtworks.meeting.parser;

import com.thoughtworks.meeting.event.Event;

/**
 * Created by sven on 14-12-25.
 */
public class StringProxy{
    private Parser parser;
    public StringProxy(Parser parser, String info){
        try {
            this.parser = parser.parse(info);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Event getEvent(){
        return this.parser.getEvent();
    }
    public static void main(String args[]){
        StringProxy sp = new StringProxy(ParserFactory.getInstance().create("32132 3213  32bvx 2dda"),"32132 3213  32bvx 2dda");
        System.out.println(sp.getEvent().getName());
        System.out.println(sp.getEvent().getInterval());
    }
}
