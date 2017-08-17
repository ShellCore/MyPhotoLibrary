package com.shellcore.android.myphotolibrary.main.events;

/**
 * Created by Cesar on 14/08/2017.
 */

public class MainEvent {

    public static final int LOGOUT_SUCCESS = 1;
    public static final int UPLOAD_COMPLETE = 2;
    public static final int UPLOAD_ERROR = 3;
    public static final int UPLOAD_INIT = 4;

    private int eventType;
    private String errorMessage;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
