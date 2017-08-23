package com.shellcore.android.myphotolibrary.photolist.ui;

import com.shellcore.android.myphotolibrary.db.entities.Photo;

/**
 * Created by Cesar on 22/08/2017.
 */

public interface PhotoListView {

    void showImage();
    void hideImage();

    void showProgressBar();
    void hideProgressBar();
    void saveAnimationRight();
    void saveAnimationLeft();
    void dismissAnimationUp();
    void dismissAnimationDown();

    void onPhotoSaved();
    void setPhoto(Photo photo);
    void onGetPhotoError(String error);

}
