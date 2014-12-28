package com.thoughtworks.meeting.parser;

import com.thoughtworks.meeting.parser.Parser;
import com.thoughtworks.meeting.parser.ParserFactory;
import junit.framework.TestCase;

/**
 * Created by sven on 14-12-27.
 */
public class TestParserFactory extends TestCase {
    public void testParserFactory(){
        Parser parser1 = ParserFactory.getInstance().create("123");
        Parser parser2 = ParserFactory.getInstance().create("123m");
        Parser parser3 = ParserFactory.getInstance().create("123min");
        Parser parser4 = ParserFactory.getInstance().create("lightning");
        Parser parser5 = ParserFactory.getInstance().create("min");
        Parser parser6 = ParserFactory.getInstance().create("12min 123");
        Parser parser7 = ParserFactory.getInstance().create("adf 123min");
        assertNull(parser1);
        assertNull(parser2);
        assertNotNull(parser3);
        assertNotNull(parser4);
        assertNotNull(parser5);
        assertNull(parser6);
        assertNotNull(parser7);
    }
}
