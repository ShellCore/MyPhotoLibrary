package com.shellcore.android.myphotolibrary.inspectphotos.di;

import com.shellcore.android.myphotolibrary.domains.FlickrClient;
import com.shellcore.android.myphotolibrary.domains.FlickrService;
import com.shellcore.android.myphotolibrary.inspectphotos.InspectPhotoInteractor;
import com.shellcore.android.myphotolibrary.inspectphotos.InspectPhotoInteractorImpl;
import com.shellcore.android.myphotolibrary.inspectphotos.InspectPhotoPresenter;
import com.shellcore.android.myphotolibrary.inspectphotos.InspectPhotoPresenterImpl;
import com.shellcore.android.myphotolibrary.inspectphotos.InspectPhotoRepository;
import com.shellcore.android.myphotolibrary.inspectphotos.InspectPhotoRepositoryImpl;
import com.shellcore.android.myphotolibrary.inspectphotos.ui.InspectPhotoView;
import com.shellcore.android.myphotolibrary.libs.base.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Cesar on 22/08/2017.
 */

@Module
public class InspectPhotoModule {

    private InspectPhotoView view;

    public InspectPhotoModule(InspectPhotoView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    InspectPhotoPresenter provides(EventBus eventBus, InspectPhotoView view, InspectPhotoInteractor interactor) {
        return new InspectPhotoPresenterImpl(eventBus, view, interactor);
    }

    @Provides
    @Singleton
    InspectPhotoView providesInspectPhotoView() {
        return view;
    }

    @Provides
    @Singleton
    InspectPhotoInteractor providesInspectPhotoInteractor(InspectPhotoRepository repository) {
        return new InspectPhotoInteractorImpl(repository);
    }

    @Provides
    @Singleton
    InspectPhotoRepository providesInspectPhotoRepository(EventBus eventBus, FlickrService service) {
        return new InspectPhotoRepositoryImpl(eventBus, service);
    }

    @Provides
    @Singleton
    FlickrService providesFlickrService() {
        return new FlickrClient().getFlickrService();
    }
}
