package com.shellcore.android.myphotolibrary.login;

import com.shellcore.android.myphotolibrary.login.events.LoginEvent;

/**
 * Created by Cesar on 07/08/2017.
 */

public interface LoginPresenter {

    void onCreate();
    void onDestroy();

    void registerNewUser(String user, String pass);
    void login(String user, String pass);
    void checkForAuthenticatedUser();

    void onEventMainthread(LoginEvent event);
}
