package com.shellcore.android.myphotolibrary.inspectphotos.ui;

import com.shellcore.android.myphotolibrary.db.entities.Photo;

import java.util.List;

/**
 * Created by Cesar on 18/08/2017.
 */

public interface InspectPhotoView {

    void enableInputs();
    void disableInputs();
    void showProgressBar();
    void hideProgressBar();

    void handleBtnSearch();
    void navigateToPhotoListView(List<Photo> flickrPhotos);
    void showMessage(String errorMessage);
}
