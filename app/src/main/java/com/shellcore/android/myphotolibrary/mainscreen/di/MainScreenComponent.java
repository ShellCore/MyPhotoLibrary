package com.shellcore.android.myphotolibrary.mainscreen.di;

import com.shellcore.android.myphotolibrary.libs.di.LibsModule;
import com.shellcore.android.myphotolibrary.mainscreen.ui.MainScreenFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Cesar on 17/08/2017.
 */

@Singleton
@Component(modules = {LibsModule.class, MainScreenModule.class})
public interface MainScreenComponent {
    void inject(MainScreenFragment fragment);
}
