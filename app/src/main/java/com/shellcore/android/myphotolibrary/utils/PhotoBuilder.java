package com.shellcore.android.myphotolibrary.utils;

import com.shellcore.android.myphotolibrary.domains.Photo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cesar on 22/08/2017.
 */

public class PhotoBuilder {
    public static List<com.shellcore.android.myphotolibrary.db.entities.Photo> getLocalPhotos(List<Photo> flickrPhotos) {
        List<com.shellcore.android.myphotolibrary.db.entities.Photo> localPhotos = new ArrayList<>();
        for (Photo photo: flickrPhotos) {
            localPhotos.add(localPhoto(photo));
        }
        return localPhotos;
    }

    private static com.shellcore.android.myphotolibrary.db.entities.Photo localPhoto(Photo flickrPhoto) {
        com.shellcore.android.myphotolibrary.db.entities.Photo localPhoto = new com.shellcore.android.myphotolibrary.db.entities.Photo();
        localPhoto.setTitle(flickrPhoto.getTitle());
        localPhoto.setUrl(flickrPhoto.getFlickrUrl());
        return localPhoto;
    }
}
