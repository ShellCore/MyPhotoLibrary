package com.shellcore.android.myphotolibrary.mainscreen;

import com.shellcore.android.myphotolibrary.mainscreen.events.MainScreenEvent;

/**
 * Created by Cesar on 17/08/2017.
 */

public interface MainScreenPresenter {

    void onCreate();
    void onDestroy();

    void readLastPhoto();

    void onEventMainThread(MainScreenEvent event);
}
