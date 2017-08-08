package com.shellcore.android.myphotolibrary.login.ui;

/**
 * Created by Cesar on 07/08/2017.
 */

public interface LoginView {

    void enableInputs();
    void disableInputs();
    void showProgressbar();
    void hideProgressbar();

    void handleSignInUp();

    void navigateToMainScreen();

    void loginError(String error);
    void newUserError(String error);

    void newUserSuccess();

}
