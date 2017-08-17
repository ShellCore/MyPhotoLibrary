package com.shellcore.android.myphotolibrary.mainscreen.events;

import com.shellcore.android.myphotolibrary.db.entities.Photo;

/**
 * Created by Cesar on 17/08/2017.
 */

public class MainScreenEvent {

    public static final int ON_READ_SUCCESS = 1;
    public static final int ON_READ_ERROR = 2;

    private int eventType;
    private String errorMessage;
    private Photo lastPhoto;

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

    public Photo getLastPhoto() {
        return lastPhoto;
    }

    public void setLastPhoto(Photo lastPhoto) {
        this.lastPhoto = lastPhoto;
    }
}
