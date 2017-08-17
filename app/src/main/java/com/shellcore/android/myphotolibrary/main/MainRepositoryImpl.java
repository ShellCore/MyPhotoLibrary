package com.shellcore.android.myphotolibrary.main;

import com.google.firebase.auth.FirebaseAuth;
import com.shellcore.android.myphotolibrary.db.entities.Photo;
import com.shellcore.android.myphotolibrary.libs.base.EventBus;
import com.shellcore.android.myphotolibrary.libs.base.ImageStorage;
import com.shellcore.android.myphotolibrary.libs.base.ImageStorageFinishedListener;
import com.shellcore.android.myphotolibrary.main.events.MainEvent;

import java.io.File;

/**
 * Created by Cesar on 14/08/2017.
 */

public class MainRepositoryImpl implements MainRepository {

    private EventBus eventBus;
    private ImageStorage imageStorage;

    public MainRepositoryImpl(EventBus eventBus, ImageStorage imageStorage) {
        this.eventBus = eventBus;
        this.imageStorage = imageStorage;
    }

    @Override
    public void logout() {
        FirebaseAuth.getInstance()
                .signOut();
         post(MainEvent.LOGOUT_SUCCESS);
    }

    @Override
    public void uploadPhoto(String path) {
        final Photo photo = new Photo();
        photo.save();

        post(MainEvent.UPLOAD_INIT);
        imageStorage.upload(new File(path), String.valueOf(photo.getId()), new ImageStorageFinishedListener(){

            @Override
            public void onSuccess() {
                String url = imageStorage.getImageUrl(String.valueOf(photo.getId()));
                photo.setUrl(url);
                photo.update();

                post(MainEvent.UPLOAD_COMPLETE);
            }

            @Override
            public void onError(String error) {
                post(MainEvent.UPLOAD_ERROR, error);
            }
        });
    }

    private void post(int type, String error){
        MainEvent event = new MainEvent();
        event.setEventType(type);
        event.setErrorMessage(error);
        eventBus.post(event);
    }

    private void post(int eventType) {
        MainEvent event = new MainEvent();
        event.setEventType(eventType);
        eventBus.post(event);
    }
}
