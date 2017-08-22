package com.shellcore.android.myphotolibrary.inspectphotos.di;

import com.shellcore.android.myphotolibrary.inspectphotos.ui.InspectPhotoFragment;
import com.shellcore.android.myphotolibrary.libs.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Cesar on 22/08/2017.
 */

@Singleton
@Component(modules = {LibsModule.class, InspectPhotoModule.class})
public interface InspectPhotoComponent {
    void inject(InspectPhotoFragment fragment);
}
