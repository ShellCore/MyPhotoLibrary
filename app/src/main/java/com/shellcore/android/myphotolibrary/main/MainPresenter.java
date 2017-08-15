package com.shellcore.android.myphotolibrary.main;

import com.shellcore.android.myphotolibrary.main.events.MainEvent;

/**
 * Created by Cesar on 14/08/2017.
 */

public interface MainPresenter {

    void onCreate();
    void onDestroy();

    void onEventMainThread(MainEvent event);

    void logout();
}
