package com.shellcore.android.myphotolibrary.photogallery;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.shellcore.android.myphotolibrary.db.entities.Photo;
import com.shellcore.android.myphotolibrary.db.entities.Photo_Table;
import com.shellcore.android.myphotolibrary.libs.base.EventBus;
import com.shellcore.android.myphotolibrary.photogallery.events.GalleryEvent;

import java.util.List;

/**
 * Created by Cesar on 17/08/2017.
 */

public class GalleryRepositoryImpl implements GalleryRepository {

    private EventBus eventBus;

    public GalleryRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void getPhotos() {
        List<Photo> photos = SQLite.select()
                .from(Photo.class)
                .orderBy(Photo_Table.id, true)
                .queryList();
        if (!photos.isEmpty()) {
            post(GalleryEvent.ON_READ_SUCCESS, photos);
        } else {
            post(GalleryEvent.ON_READ_ERROR, "There's no photos in database");
        }
    }

    private void post(int eventType, List<Photo> photos, String errorMessage) {
        GalleryEvent event = new GalleryEvent();
        event.setEventType(eventType);
        event.setPhotos(photos);
        event.setErrorMessage(errorMessage);
        eventBus.post(event);
    }

    private void post(int eventType, List<Photo> photos) {
        post(eventType, photos, null);
    }

    private void post(int eventType, String errorMessage) {
        post(eventType, null, errorMessage);
    }
}
