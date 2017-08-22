package com.shellcore.android.myphotolibrary.inspectphotos.events;

import com.shellcore.android.myphotolibrary.db.entities.Photo;

import java.util.List;

/**
 * Created by Cesar on 22/08/2017.
 */

public class InspectPhotoEvent {

    private List<Photo> flickrPhotos;
    private String errorMessage;

    public List<Photo> getFlickrPhotos() {
        return flickrPhotos;
    }

    public void setFlickrPhotos(List<Photo> flickrPhotos) {
        this.flickrPhotos = flickrPhotos;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
