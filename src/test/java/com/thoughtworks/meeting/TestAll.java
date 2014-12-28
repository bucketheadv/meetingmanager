package com.thoughtworks.meeting;

import com.thoughtworks.meeting.event.TestEventGeneratorImpl;
import com.thoughtworks.meeting.event.TestNetworkingEventController;
import com.thoughtworks.meeting.io.TestConfigReader;
import com.thoughtworks.meeting.io.TestEventReader;
import com.thoughtworks.meeting.parser.TestParserFactory;
import com.thoughtworks.meeting.parser.TestStringProxy;
import com.thoughtworks.meeting.utils.TestTimeUtils;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
/**
 * Created by sven on 14-12-24.
 */
public class TestAll extends TestSuite{
    public static Test suite(){
        TestSuite suite = new TestSuite("TestSuite Test");
        suite.addTestSuite(TestConfigReader.class);
        suite.addTestSuite(TestEventReader.class);
        suite.addTestSuite(TestStringProxy.class);
        suite.addTestSuite(TestTimeUtils.class);
        suite.addTestSuite(TestEventGeneratorImpl.class);
        suite.addTestSuite(TestParserFactory.class);
        suite.addTestSuite(TestNetworkingEventController.class);
        return suite;
    }
    public static void main(String args[]){
        TestRunner.run(suite());
    }
}
