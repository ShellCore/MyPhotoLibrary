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
                view.navigateToLoginView();
        }
    }

    @Override
    public void logout() {
        interactor.logout();
    }
}
