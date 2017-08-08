package com.shellcore.android.myphotolibrary;

import android.app.Activity;
import android.app.Application;

import com.shellcore.android.myphotolibrary.libs.di.LibsModule;
import com.shellcore.android.myphotolibrary.login.di.DaggerLoginComponent;
import com.shellcore.android.myphotolibrary.login.di.LoginComponent;
import com.shellcore.android.myphotolibrary.login.di.LoginModule;
import com.shellcore.android.myphotolibrary.login.ui.LoginView;

/**
 * Created by Cesar on 08/08/2017.
 */

public class MyPhotoLibraryApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public LoginComponent getLoginComponent(Activity activity, LoginView view) {
        return DaggerLoginComponent.builder()
                .libsModule(new LibsModule(activity))
                .loginModule(new LoginModule(view))
                .build();
    }
}
