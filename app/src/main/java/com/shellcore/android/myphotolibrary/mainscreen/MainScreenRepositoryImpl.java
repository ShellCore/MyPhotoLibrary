package com.shellcore.android.myphotolibrary.mainscreen;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.shellcore.android.myphotolibrary.db.entities.Photo;
import com.shellcore.android.myphotolibrary.db.entities.Photo_Table;
import com.shellcore.android.myphotolibrary.libs.base.EventBus;
import com.shellcore.android.myphotolibrary.mainscreen.events.MainScreenEvent;

import java.util.List;

/**
 * Created by Cesar on 17/08/2017.
 */

public class MainScreenRepositoryImpl implements MainScreenRepository {

    private EventBus eventBus;

    public MainScreenRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void readLastPhoto() {
        List<Photo> list = SQLite.select()
                .from(Photo.class)
                .orderBy(Photo_Table.id, false)
                .queryList();
        if (!list.isEmpty()) {
            post(MainScreenEvent.ON_READ_SUCCESS, list.get(0));
        } else {
            post(MainScreenEvent.ON_READ_ERROR, "There's no photos in database");
        }
    }

    private void post(int eventType, Photo lastPhoto, String errorMessage) {
        MainScreenEvent event = new MainScreenEvent();
        event.setEventType(eventType);
        event.setLastPhoto(lastPhoto);
        event.setErrorMessage(errorMessage);
        eventBus.post(event);
    }

    private void post(int eventType, Photo lastPhoto) {
        post(eventType, lastPhoto, null);
    }

    private void post(int eventType, String errorMessage) {
        post(eventType, null, errorMessage);
    }
}
