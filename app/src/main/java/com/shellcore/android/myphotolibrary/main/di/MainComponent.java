package com.shellcore.android.myphotolibrary.main.di;

import com.shellcore.android.myphotolibrary.libs.di.LibsModule;
import com.shellcore.android.myphotolibrary.main.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Cesar on 15/08/2017.
 */
@Singleton
@Component(modules = {LibsModule.class, MainModule.class})
public interface MainComponent {
    void inject(MainActivity activity);
}
