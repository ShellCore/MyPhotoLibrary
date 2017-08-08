package com.shellcore.android.myphotolibrary.login.di;

import com.shellcore.android.myphotolibrary.libs.di.LibsModule;
import com.shellcore.android.myphotolibrary.login.ui.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Cesar on 08/08/2017.
 */

@Singleton
@Component(modules = {LibsModule.class, LoginModule.class})
public interface LoginComponent {
    void inject(LoginActivity activity);
}
