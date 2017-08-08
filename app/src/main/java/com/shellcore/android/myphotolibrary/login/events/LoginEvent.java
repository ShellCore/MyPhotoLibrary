package com.shellcore.android.myphotolibrary.login.events;

/**
 * Created by Cesar on 07/08/2017.
 */

public class LoginEvent {

    public static final int ON_SIGNIN_SUCCESS = 1;
    public static final int ON_SIGNUP_SUCCESS = 2;
    public static final int ON_SIGNIN_ERROR = 3;
    public static final int ON_SIGNUP_ERROR = 4;
    public static final int ON_FAILED_TO_RECOVER_SESSION = 5;

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
