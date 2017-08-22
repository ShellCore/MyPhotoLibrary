package com.shellcore.android.myphotolibrary.photolist.ui;

import com.shellcore.android.myphotolibrary.db.entities.Photo;

/**
 * Created by Cesar on 22/08/2017.
 */

public interface PhotoListView {

    void showProgressBar();
    void hideProgressBar();
    void saveAnimation();
    void dismissAnimation();

    void onPhotoSaved();
    void setPhoto(Photo photo);
    void onGetPhotoError(String error);

}
