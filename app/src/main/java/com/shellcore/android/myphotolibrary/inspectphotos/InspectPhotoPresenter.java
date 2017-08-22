package com.shellcore.android.myphotolibrary.inspectphotos;

import com.shellcore.android.myphotolibrary.inspectphotos.events.InspectPhotoEvent;

/**
 * Created by Cesar on 22/08/2017.
 */

public interface InspectPhotoPresenter {

    void onResume();
    void onPause();
    void onDestroy();

    void onEventMainThread(InspectPhotoEvent event);

    void searchImages(String tags);
}
