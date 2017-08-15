package com.shellcore.android.myphotolibrary.main.events;

/**
 * Created by Cesar on 14/08/2017.
 */

public class MainEvent {

    public static final int LOGOUT_SUCCESS = 1;

    private int eventType;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }
}
