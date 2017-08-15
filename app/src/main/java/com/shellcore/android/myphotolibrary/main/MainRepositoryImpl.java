package com.shellcore.android.myphotolibrary.main;

import com.google.firebase.auth.FirebaseAuth;
import com.shellcore.android.myphotolibrary.libs.base.EventBus;
import com.shellcore.android.myphotolibrary.main.events.MainEvent;

/**
 * Created by Cesar on 14/08/2017.
 */

public class MainRepositoryImpl implements MainRepository {

    private EventBus eventBus;

    public MainRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void logout() {
        FirebaseAuth.getInstance()
                .signOut();
         post(MainEvent.LOGOUT_SUCCESS);
    }

    private void post(int eventType) {
        MainEvent event = new MainEvent();
        event.setEventType(eventType);
        eventBus.post(event);
    }
}
