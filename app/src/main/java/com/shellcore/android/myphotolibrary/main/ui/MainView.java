package com.shellcore.android.myphotolibrary.main.ui;

/**
 * Created by Cesar on 14/08/2017.
 */

public interface MainView {

    void handleMainScreen();
    void handleCamera();
    void handleMyGallery();
    void handleInspectPhotos();
    void handleShare();
    void handleLogout();

    void navigateToLoginView();
}
