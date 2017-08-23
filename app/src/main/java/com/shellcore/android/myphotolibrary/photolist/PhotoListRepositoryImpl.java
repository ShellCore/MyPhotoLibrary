package com.shellcore.android.myphotolibrary.photolist;

import com.shellcore.android.myphotolibrary.db.entities.Photo;
import com.shellcore.android.myphotolibrary.domains.FlickrService;
import com.shellcore.android.myphotolibrary.domains.PhotosResponse;
import com.shellcore.android.myphotolibrary.libs.base.EventBus;
import com.shellcore.android.myphotolibrary.photolist.events.PhotoListEvent;
import com.shellcore.android.myphotolibrary.utils.PhotoBuilder;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Cesar on 22/08/2017.
 */

public class PhotoListRepositoryImpl implements PhotoListRepository {

    // Constants
    private static final int PHOTO_COUNT = 30;

    // Variables
    private String tags;

    // Services
    private EventBus eventBus;
    private FlickrService service;


    public PhotoListRepositoryImpl(EventBus eventBus, FlickrService service) {
        this.eventBus = eventBus;
        this.service = service;
    }

    @Override
    public void getNextPhoto(String tags) {
        this.tags = tags;
        Call<PhotosResponse> call = service.search(tags, PHOTO_COUNT);
        call.enqueue(new Callback<PhotosResponse>() {
            @Override
            public void onResponse(Call<PhotosResponse> call, Response<PhotosResponse> response) {
                if (response.isSuccessful()) {
                    PhotosResponse photosResponse = response.body();
                    List<Photo> photoList = PhotoBuilder.getLocalPhotos(photosResponse.getPhotos().getPhoto());
                    int random = new Random().nextInt(PHOTO_COUNT);
                    post(photoList.get(random));
                } else {
                    post(response.message());
                }
            }

            @Override
            public void onFailure(Call<PhotosResponse> call, Throwable t) {
                post(t.getLocalizedMessage());
            }
        });
    }


    @Override
    public void savePhoto(Photo photo) {
        photo.save();
        post();
    }

    private void post(int eventType, Photo photo, String errorMessage, String tags) {
        PhotoListEvent event = new PhotoListEvent();
        event.setEventType(eventType);
        event.setPhoto(photo);
        event.setErrorMessage(errorMessage);
        eventBus.post(event);
    }

    private void post(Photo photo) {
        post(PhotoListEvent.NEXT_PHOTO, photo, null, null);
    }

    private void post(String errorMessage) {
        post(PhotoListEvent.NEXT_PHOTO, null, errorMessage, null);
    }

    private void post() {
        post(PhotoListEvent.SAVE_PHOTO, null, null, null);
    }
}
