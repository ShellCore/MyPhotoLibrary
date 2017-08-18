package com.shellcore.android.myphotolibrary.photogallery.di;

import com.shellcore.android.myphotolibrary.libs.di.LibsModule;
import com.shellcore.android.myphotolibrary.photogallery.ui.GalleryFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Cesar on 17/08/2017.
 */
@Singleton
@Component(modules = {LibsModule.class, GalleryModule.class})
public interface GalleryComponent {
    void inject(GalleryFragment fragment);
}
