package com.shellcore.android.myphotolibrary.photolist.di;

import com.shellcore.android.myphotolibrary.domains.FlickrClient;
import com.shellcore.android.myphotolibrary.domains.FlickrService;
import com.shellcore.android.myphotolibrary.libs.base.EventBus;
import com.shellcore.android.myphotolibrary.photolist.NextPhotoInteractor;
import com.shellcore.android.myphotolibrary.photolist.NextPhotoInteractorImpl;
import com.shellcore.android.myphotolibrary.photolist.PhotoListPresenter;
import com.shellcore.android.myphotolibrary.photolist.PhotoListPresenterImpl;
import com.shellcore.android.myphotolibrary.photolist.PhotoListRepository;
import com.shellcore.android.myphotolibrary.photolist.PhotoListRepositoryImpl;
import com.shellcore.android.myphotolibrary.photolist.SavePhotoInteractor;
import com.shellcore.android.myphotolibrary.photolist.SavePhotoInteractorImpl;
import com.shellcore.android.myphotolibrary.photolist.ui.PhotoListView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Cesar on 22/08/2017.
 */

@Module
public class PhotoListModule {

    private PhotoListView view;

    public PhotoListModule(PhotoListView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    PhotoListPresenter providesPhotoListPresenter(EventBus eventBus, PhotoListView view, SavePhotoInteractor saveInteractor, NextPhotoInteractor nextInteractor) {
        return new PhotoListPresenterImpl(eventBus, view, saveInteractor, nextInteractor);
    }

    @Provides
    @Singleton
    PhotoListView providesPhotoListView() {
        return view;
    }

    @Provides
    @Singleton
    NextPhotoInteractor providesNextPhotoInteractor(PhotoListRepository repository) {
        return new NextPhotoInteractorImpl(repository);
    }

    @Provides
    @Singleton
    SavePhotoInteractor providesSavePhotoInteractor(PhotoListRepository repository) {
        return new SavePhotoInteractorImpl(repository);
    }

    @Provides
    @Singleton
    PhotoListRepository providesPhotoListRepository(EventBus eventBus, FlickrService service) {
        return new PhotoListRepositoryImpl(eventBus, service);
    }

    @Provides
    @Singleton
    FlickrService providesFlickrService() {
        return new FlickrClient().getFlickrService();
    }
}
