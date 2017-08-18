package com.shellcore.android.myphotolibrary.photogallery.di;

import com.shellcore.android.myphotolibrary.db.entities.Photo;
import com.shellcore.android.myphotolibrary.libs.base.EventBus;
import com.shellcore.android.myphotolibrary.libs.base.ImageLoader;
import com.shellcore.android.myphotolibrary.photogallery.GalleryInteractor;
import com.shellcore.android.myphotolibrary.photogallery.GalleryInteractorImpl;
import com.shellcore.android.myphotolibrary.photogallery.GalleryPresenter;
import com.shellcore.android.myphotolibrary.photogallery.GalleryPresenterImpl;
import com.shellcore.android.myphotolibrary.photogallery.GalleryRepository;
import com.shellcore.android.myphotolibrary.photogallery.GalleryRepositoryImpl;
import com.shellcore.android.myphotolibrary.photogallery.adapters.GalleryAdapter;
import com.shellcore.android.myphotolibrary.photogallery.adapters.OnItemClickListener;
import com.shellcore.android.myphotolibrary.photogallery.ui.GalleryView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Cesar on 17/08/2017.
 */

@Module
public class GalleryModule {

    private GalleryView view;
    private OnItemClickListener clickListener;

    public GalleryModule(GalleryView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    GalleryAdapter providesGalleryAdapter(ImageLoader imageLoader, List<Photo> list, OnItemClickListener clickListener) {
        return new GalleryAdapter(imageLoader, list, clickListener);
    }

    @Provides
    @Singleton
    List<Photo> providesPhotoList() {
        return new ArrayList<Photo>();
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener() {
        return clickListener;
    }

    @Provides
    @Singleton
    GalleryPresenter providesGalleryPresenter(EventBus eventBus, GalleryView view, GalleryInteractor interactor) {
        return new GalleryPresenterImpl(eventBus, view, interactor);
    }

    @Provides
    @Singleton
    GalleryView providesGalleryView() {
        return view;
    }

    @Provides
    @Singleton
    GalleryInteractor providesGalleryInteractor(GalleryRepository repository) {
        return new GalleryInteractorImpl(repository);
    }

    @Provides
    @Singleton
    GalleryRepository providesGalleryRepository(EventBus eventBus) {
        return new GalleryRepositoryImpl(eventBus);
    }
}
