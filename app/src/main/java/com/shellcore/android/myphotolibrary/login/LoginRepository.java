package com.shellcore.android.myphotolibrary.login;

/**
 * Created by Cesar on 07/08/2017.
 */

public interface LoginRepository {

    void signin(String user, String pass);
    void signup(String user, String pass);
    void checkAlreadyAuthenticated();
}
