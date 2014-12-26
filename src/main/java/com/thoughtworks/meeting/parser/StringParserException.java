package com.thoughtworks.meeting.parser;

/**
 * Created by sven on 14-12-25.
 */
public class StringParserException extends Exception {
    public StringParserException(){
        super("String parser exception");
    }
    public StringParserException(String info){
        super("String parser exception: " + info);
    }
}
