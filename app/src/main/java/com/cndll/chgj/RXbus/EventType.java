package com.cndll.chgj.RXbus;

/**
 * Created by kongqing on 2017/5/9.
 */

public class EventType {
    public final static int BACKKEY = 0;

    public int getType() {
        return type;
    }

    public EventType setType(int type) {
        this.type = type;
        return this;
    }

    private int type;
}
