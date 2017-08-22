package com.shellcore.android.myphotolibrary.photolist;

import com.shellcore.android.myphotolibrary.db.entities.Photo;
import com.shellcore.android.myphotolibrary.photolist.events.PhotoListEvent;

/**
 * Created by Cesar on 22/08/2017.
 */

public interface PhotoListPresenter {

    void onCreate();
    void onDestroy();

    void dismissPhoto();
    void getNextPhoto(String tags);
    void savePhoto(Photo currentPhoto);

    void imageReady();
    void imageError(String errorMessage);

    void onEventMainThread(PhotoListEvent event);
}
