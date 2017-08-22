package com.shellcore.android.myphotolibrary.inspectphotos;

/**
 * Created by Cesar on 22/08/2017.
 */

public class InspectPhotoInteractorImpl implements InspectPhotoInteractor {

    private InspectPhotoRepository repository;

    public InspectPhotoInteractorImpl(InspectPhotoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void searchImages(String tags) {
        repository.searchImages(tags);
    }
}
