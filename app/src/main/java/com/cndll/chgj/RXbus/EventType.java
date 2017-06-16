package com.cndll.chgj.RXbus;

/**
 * Created by kongqing on 2017/5/9.
 */

public class EventType {
    public final static int BACKKEY = 0;
    public final static int SHOW = 1;
    public final static int RESET = 2;

    public int getType() {
        return type;
    }

    public EventType setType(int type) {
        this.type = type;
        return this;
    }

    public String getExtra() {
        return extra;
    }

    public EventType setExtra(String extra) {
        this.extra = extra;
        return this;
    }

    private String extra;
    private int type;
}
