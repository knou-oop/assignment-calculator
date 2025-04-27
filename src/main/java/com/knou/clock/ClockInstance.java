package com.knou.clock;

import java.time.Clock;
import java.time.ZoneId;

public class ClockInstance {

    public Clock getClock() {
       return Clock.system(ZoneId.of("UTC"));
    }
}
