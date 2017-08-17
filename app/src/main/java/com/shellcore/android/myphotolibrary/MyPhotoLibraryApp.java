package com.shellcore.android.myphotolibrary;

import android.app.Activity;
import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.shellcore.android.myphotolibrary.libs.di.LibsModule;
import com.shellcore.android.myphotolibrary.login.di.DaggerLoginComponent;
import com.shellcore.android.myphotolibrary.login.di.LoginComponent;
import com.shellcore.android.myphotolibrary.login.di.LoginModule;
import com.shellcore.android.myphotolibrary.login.ui.LoginView;
import com.shellcore.android.myphotolibrary.main.di.DaggerMainComponent;
import com.shellcore.android.myphotolibrary.main.di.MainComponent;
import com.shellcore.android.myphotolibrary.main.di.MainModule;
import com.shellcore.android.myphotolibrary.main.ui.MainView;
import com.shellcore.android.myphotolibrary.mainscreen.di.DaggerMainScreenComponent;
import com.shellcore.android.myphotolibrary.mainscreen.di.MainScreenComponent;
import com.shellcore.android.myphotolibrary.mainscreen.di.MainScreenModule;
import com.shellcore.android.myphotolibrary.mainscreen.ui.MainScreenView;

/**
 * Created by Cesar on 08/08/2017.
 */

public class MyPhotoLibraryApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setupDatabase();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        tearDownDatabase();
    }

    private void setupDatabase() {
        FlowManager.init(this);
    }

    private void tearDownDatabase() {
        FlowManager.destroy();
    }

    public LoginComponent getLoginComponent(Activity activity, LoginView view) {
        return DaggerLoginComponent.builder()
                .libsModule(new LibsModule(activity, activity.getApplicationContext()))
                .loginModule(new LoginModule(view))
                .build();
    }

    public MainComponent getMainComponent(Activity activity, MainView view) {
        return DaggerMainComponent.builder()
                .libsModule(new LibsModule(activity, activity.getApplicationContext()))
                .mainModule(new MainModule(view))
                .build();
    }

    public MainScreenComponent getMainScreenComponent(Activity activity, MainScreenView view) {
        return DaggerMainScreenComponent.builder()
                .libsModule(new LibsModule(activity, activity.getApplicationContext()))
                .mainScreenModule(new MainScreenModule(view))
                .build();
    }
}
