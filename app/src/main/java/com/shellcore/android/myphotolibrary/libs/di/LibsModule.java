package com.shellcore.android.myphotolibrary.libs.di;

import android.app.Activity;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.cloudinary.Cloudinary;
import com.cloudinary.android.Utils;
import com.shellcore.android.myphotolibrary.libs.CloudinaryImageStorage;
import com.shellcore.android.myphotolibrary.libs.GlideImageLoader;
import com.shellcore.android.myphotolibrary.libs.GreenRobotEventBus;
import com.shellcore.android.myphotolibrary.libs.base.EventBus;
import com.shellcore.android.myphotolibrary.libs.base.ImageLoader;
import com.shellcore.android.myphotolibrary.libs.base.ImageStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Cesar on 07/08/2017.
 */

@Module
public class LibsModule {

    private Activity activity;
    private Context context;

    public LibsModule(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(RequestManager requestManager) {
        return new GlideImageLoader(requestManager);
    }

    @Provides
    @Singleton
    RequestManager providesRequestManager(Activity activity) {
        return Glide.with(activity);
    }

    @Provides
    @Singleton
    Activity providesActivity() {
        return activity;
    }

    @Provides
    @Singleton
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus) {
        return new GreenRobotEventBus(eventBus);
    }

    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus() {
        return org.greenrobot.eventbus.EventBus.getDefault();
    }

    @Provides
    @Singleton
    ImageStorage providesImageStorage(EventBus eventBus, Cloudinary cloudinary) {
        return new CloudinaryImageStorage(eventBus, cloudinary);
    }

    @Provides
    @Singleton
    Cloudinary providesCloudinary(Context context) {
        return new Cloudinary(Utils.cloudinaryUrlFromContext(context));
    }

    @Provides
    @Singleton
    Context providesContext() {
        return context;
    }
}
