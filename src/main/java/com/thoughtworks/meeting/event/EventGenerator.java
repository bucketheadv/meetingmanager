package com.thoughtworks.meeting.event;

import java.util.List;

/**
 * Created by sven on 14-12-24.
 */
public interface EventGenerator {
    public void generateMorningEvent();
    public void generateLunchEvent();
    public void generateAfternoonEvent();
    public void generateEveningEvent();

    public List<EventResult> getTrack();
}
