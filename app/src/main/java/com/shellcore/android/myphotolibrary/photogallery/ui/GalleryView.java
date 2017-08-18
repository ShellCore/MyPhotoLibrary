package com.shellcore.android.myphotolibrary.photogallery.ui;

import com.shellcore.android.myphotolibrary.db.entities.Photo;

import java.util.List;

/**
 * Created by Cesar on 17/08/2017.
 */

public interface GalleryView {

    void showGalleryList();
    void hideGalleryList();
    void showMessageNoPhotos();
    void hideMessageNoPhotos();

    void showMessage(String msg);

    void setContent(List<Photo> list);
}
