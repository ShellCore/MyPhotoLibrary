package com.shellcore.android.myphotolibrary.photogallery;

/**
 * Created by Cesar on 17/08/2017.
 */

public class GalleryInteractorImpl implements GalleryInteractor {

    private GalleryRepository repository;

    public GalleryInteractorImpl(GalleryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getPhotos() {
        repository.getPhotos();
    }
}
