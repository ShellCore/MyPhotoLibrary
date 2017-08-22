package com.shellcore.android.myphotolibrary.inspectphotos;

import com.shellcore.android.myphotolibrary.db.entities.Photo;
import com.shellcore.android.myphotolibrary.domains.FlickrService;
import com.shellcore.android.myphotolibrary.domains.PhotosResponse;
import com.shellcore.android.myphotolibrary.inspectphotos.events.InspectPhotoEvent;
import com.shellcore.android.myphotolibrary.libs.base.EventBus;
import com.shellcore.android.myphotolibrary.utils.PhotoBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Cesar on 22/08/2017.
 */

public class InspectPhotoRepositoryImpl implements InspectPhotoRepository {

    // Constants
    private static final int PHOTO_COUNT = 30;

    // Variables;

    // Services
    private EventBus eventBus;
    private FlickrService service;

    public InspectPhotoRepositoryImpl(EventBus eventBus, FlickrService service) {
        this.eventBus = eventBus;
        this.service = service;
    }

    @Override
    public void searchImages(String tags) {
        Call<PhotosResponse> call = service.search(tags, PHOTO_COUNT);
        call.enqueue(new Callback<PhotosResponse>() {
            @Override
            public void onResponse(Call<PhotosResponse> call, Response<PhotosResponse> response) {
                if (response.isSuccessful()) {
                    PhotosResponse photosResponse = response.body();
                    List<Photo> photoList = PhotoBuilder.getLocalPhotos(photosResponse.getPhotos().getPhoto());
                    post(photoList);
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

    private void post(List<Photo> list, String errorMessage) {
        InspectPhotoEvent event = new InspectPhotoEvent();
        event.setFlickrPhotos(list);
        event.setErrorMessage(errorMessage);
        eventBus.post(event);
    }

    private void post(List<Photo> list) {
        post(list, null);
    }

    private void post(String errorMessage) {
        post(null, errorMessage);
    }
}
