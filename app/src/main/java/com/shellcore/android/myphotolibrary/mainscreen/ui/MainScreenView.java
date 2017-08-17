package com.shellcore.android.myphotolibrary.mainscreen.ui;

import com.shellcore.android.myphotolibrary.db.entities.Photo;

/**
 * Created by Cesar on 17/08/2017.
 */

public interface MainScreenView {

    void showImage();
    void hideImage();
    void showTxtNoPhotos();
    void hideTxtNoPhotos();

    void setLastPhoto(Photo photo);
    void setErrorMessage(String errorMessage);

    void handleBtnShare();
}
