package com.shellcore.android.myphotolibrary.main;

import com.shellcore.android.myphotolibrary.libs.base.EventBus;
import com.shellcore.android.myphotolibrary.main.events.MainEvent;
import com.shellcore.android.myphotolibrary.main.ui.MainView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Cesar on 14/08/2017.
 */

public class MainPresenterImpl implements MainPresenter {

    private EventBus eventBus;
    private MainView view;

    private MainInteractor interactor;

    public MainPresenterImpl(EventBus eventBus, MainView view, MainInteractor interactor) {
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

    @Subscribe
    @Override
    public void onEventMainThread(MainEvent event) {
        switch (event.getEventType()) {
            case MainEvent.LOGOUT_SUCCESS:
                logoutSuccess();
                break;
            case MainEvent.UPLOAD_INIT:
                showMessage("Uploading photo");
                break;
            case MainEvent.UPLOAD_COMPLETE:
                showMessage("The photo has been uploaded");
                updatePhotos();
                break;
            case MainEvent.UPLOAD_ERROR:
                showMessage(event.getErrorMessage());
                break;
        }
    }

    @Override
    public void logout() {
        interactor.logout();
    }

    private void updatePhotos() {
        if (view != null) {
            view.updatePhotos();
        }
    }

    @Override
    public void uploadPhoto(String uri) {
        interactor.uploadPhoto(uri);
    }

    private void showMessage(String msg) {
        if (view != null) {
            view.showMessage(msg);
        }
    }

    private void logoutSuccess() {
        if (view != null) {
            view.navigateToLoginView();
        }
    }
}
