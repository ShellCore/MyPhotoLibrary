package com.shellcore.android.myphotolibrary.login;

/**
 * Created by Cesar on 07/08/2017.
 */

public interface LoginInteractor {

    void doSignin(String user, String pass);
    void doSignup(String user, String pass);
    void checkAlreadyAuthenticated();
}
