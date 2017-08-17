package com.shellcore.android.myphotolibrary.mainscreen;

import com.shellcore.android.myphotolibrary.db.entities.Photo;
import com.shellcore.android.myphotolibrary.libs.base.EventBus;
import com.shellcore.android.myphotolibrary.mainscreen.events.MainScreenEvent;
import com.shellcore.android.myphotolibrary.mainscreen.ui.MainScreenView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Cesar on 17/08/2017.
 */

public class MainScreenPresenterImpl implements MainScreenPresenter {

    private EventBus eventBus;
    private MainScreenView view;
    private MainScreenInteractor interactor;

    public MainScreenPresenterImpl(EventBus eventBus, MainScreenView view, MainScreenInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
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
    public void readLastPhoto() {
        interactor.readLastPhoto();
    }

    @Override
    @Subscribe
    public void onEventMainThread(MainScreenEvent event) {
        switch (event.getEventType()) {
            case MainScreenEvent.ON_READ_SUCCESS:
                setPhoto(event.getLastPhoto());
                break;
            case MainScreenEvent.ON_READ_ERROR:
                showError(event.getErrorMessage());
                break;
        }
    }

    private void showError(String errorMessage) {
        if (view != null) {
            view.hideImage();
            view.showTxtNoPhotos();
            view.setErrorMessage(errorMessage);
        }
    }

    private void setPhoto(Photo lastPhoto) {
        if (view != null) {
            view.hideTxtNoPhotos();
            view.showImage();
            view.setLastPhoto(lastPhoto);
        }
    }
}
