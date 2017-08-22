package com.shellcore.android.myphotolibrary.photolist;

import com.shellcore.android.myphotolibrary.db.entities.Photo;
import com.shellcore.android.myphotolibrary.libs.base.EventBus;
import com.shellcore.android.myphotolibrary.photolist.events.PhotoListEvent;
import com.shellcore.android.myphotolibrary.photolist.ui.PhotoListView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Cesar on 22/08/2017.
 */

public class PhotoListPresenterImpl implements PhotoListPresenter {

    private EventBus eventBus;
    private PhotoListView view;
    private SavePhotoInteractor saveInteractor;
    private NextPhotoInteractor nextInteractor;
    private String tags;

    public PhotoListPresenterImpl(EventBus eventBus, PhotoListView view, SavePhotoInteractor saveInteractor, NextPhotoInteractor nextInteractor) {
        this.eventBus = eventBus;
        this.view = view;
        this.saveInteractor = saveInteractor;
        this.nextInteractor = nextInteractor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        view = null;
    }

    @Override
    public void dismissPhoto() {
        if (view != null) {
            view.dismissAnimation();
        }
        getNextPhoto(tags);
    }

    @Override
    public void getNextPhoto(String tags) {
        this.tags = tags;
        if (view != null) {
            view.showProgressBar();
        }
        nextInteractor.execute(tags);
    }

    @Override
    public void savePhoto(Photo photo) {
        if (view != null) {
            view.saveAnimation();
            view.showProgressBar();
        }
        saveInteractor.execute(photo);
    }

    @Override
    public void imageReady() {
        if (view != null) {
            view.hideProgressBar();
        }
    }

    @Override
    public void imageError(String errorMessage) {
        if (view != null) {
            view.onGetPhotoError(errorMessage);
        }
    }

    @Override
    @Subscribe
    public void onEventMainThread(PhotoListEvent event) {
        if (view != null) {
            String error = event.getErrorMessage();
            if (error != null) {
                view.hideProgressBar();
                view.onGetPhotoError(error);
            } else {
                switch (event.getEventType()) {
                    case PhotoListEvent.NEXT_PHOTO:
                        view.setPhoto(event.getPhoto());
                        break;
                    case PhotoListEvent.SAVE_PHOTO:
                        view.onPhotoSaved();
                        nextInteractor.execute(tags);
                        break;
                }
            }
        }
    }
}
