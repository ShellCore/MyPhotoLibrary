package com.shellcore.android.myphotolibrary.photolist.di;

import com.shellcore.android.myphotolibrary.libs.di.LibsModule;
import com.shellcore.android.myphotolibrary.photolist.ui.PhotoListActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Cesar on 22/08/2017.
 */

@Singleton
@Component(modules = {LibsModule.class, PhotoListModule.class})
public interface PhotoListComponent {
    void inject(PhotoListActivity activity);
}
