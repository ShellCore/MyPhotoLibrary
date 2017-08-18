package com.shellcore.android.myphotolibrary.photogallery.events;

import com.shellcore.android.myphotolibrary.db.entities.Photo;

import java.util.List;

/**
 * Created by Cesar on 17/08/2017.
 */

public class GalleryEvent {

    public static final int ON_READ_SUCCESS = 1;
    public static final int ON_READ_ERROR = 2;
    private int eventType;
    private List<Photo> photos;
    private String errorMessage;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
