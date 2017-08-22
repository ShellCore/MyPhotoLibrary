package com.shellcore.android.myphotolibrary.photolist.events;

import com.shellcore.android.myphotolibrary.db.entities.Photo;

/**
 * Created by Cesar on 22/08/2017.
 */

public class PhotoListEvent {

    public static final int NEXT_PHOTO = 1;
    public static final int SAVE_PHOTO = 2;

    private int eventType;
    private Photo photo;
    private String errorMessage;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
