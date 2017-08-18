package com.shellcore.android.myphotolibrary.photogallery;

import com.shellcore.android.myphotolibrary.photogallery.events.GalleryEvent;

/**
 * Created by Cesar on 17/08/2017.
 */

public interface GalleryPresenter {

    void onResume();
    void onPause();
    void onDestroy();

    void getPhotos();

    void onEventMainThread(GalleryEvent event);
}
