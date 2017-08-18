package com.shellcore.android.myphotolibrary.photogallery;

import com.shellcore.android.myphotolibrary.db.entities.Photo;
import com.shellcore.android.myphotolibrary.libs.base.EventBus;
import com.shellcore.android.myphotolibrary.photogallery.events.GalleryEvent;
import com.shellcore.android.myphotolibrary.photogallery.ui.GalleryView;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by Cesar on 17/08/2017.
 */

public class GalleryPresenterImpl implements GalleryPresenter {

    private EventBus eventBus;
    private GalleryView view;
    private GalleryInteractor interactor;

    public GalleryPresenterImpl(EventBus eventBus, GalleryView view, GalleryInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onResume() {
        eventBus.register(this);
    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void getPhotos() {
        if (view != null) {
            view.hideGalleryList();
            view.hideMessageNoPhotos();
        }
        interactor.getPhotos();
    }

    @Override
    @Subscribe
    public void onEventMainThread(GalleryEvent event) {
        switch (event.getEventType()) {
            case GalleryEvent.ON_READ_SUCCESS:
                readSuccess(event.getPhotos());
                break;
            case GalleryEvent.ON_READ_ERROR:
                readError(event.getErrorMessage());
                break;
        }
    }

    private void readSuccess(List<Photo> photos) {
        if (view != null) {
            view.hideMessageNoPhotos();
            view.setContent(photos);
            view.showGalleryList();
        }
    }

    private void readError(String errorMessage) {
        if (view != null) {
            view.hideGalleryList();
            view.showMessageNoPhotos();
            view.showMessage(errorMessage);
        }
    }
}
