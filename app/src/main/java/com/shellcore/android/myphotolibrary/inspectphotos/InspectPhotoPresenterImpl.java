package com.shellcore.android.myphotolibrary.inspectphotos;

import com.shellcore.android.myphotolibrary.inspectphotos.events.InspectPhotoEvent;
import com.shellcore.android.myphotolibrary.inspectphotos.ui.InspectPhotoView;
import com.shellcore.android.myphotolibrary.libs.base.EventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Cesar on 22/08/2017.
 */

public class InspectPhotoPresenterImpl implements InspectPhotoPresenter {

    private EventBus eventBus;
    private InspectPhotoView view;
    private InspectPhotoInteractor interactor;

    public InspectPhotoPresenterImpl(EventBus eventBus, InspectPhotoView view, InspectPhotoInteractor interactor) {
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
    @Subscribe
    public void onEventMainThread(InspectPhotoEvent event) {
        if (view != null) {
            view.enableInputs();
            view.hideProgressBar();
        }

        if (event.getErrorMessage() != null && !event.getErrorMessage().isEmpty()) {
            view.showMessage(event.getErrorMessage());
        } else {
            view.navigateToPhotoListView(event.getFlickrPhotos());
        }
    }

    @Override
    public void searchImages(String tags) {
        if (view != null) {
            view.disableInputs();
            view.showProgressBar();
        }
        interactor.searchImages(tags);

    }
}
