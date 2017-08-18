package com.shellcore.android.myphotolibrary.photoDetail.ui;

/**
 * Created by Cesar on 18/08/2017.
 */

public interface PhotoDetailView {

    void showTitle();
    void hideTitle();

    void setImage(String uri);
    void setTitle(String title);
}
