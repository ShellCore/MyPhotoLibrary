package com.shellcore.android.myphotolibrary.photolist;

/**
 * Created by Cesar on 22/08/2017.
 */

public class NextPhotoInteractorImpl implements NextPhotoInteractor {

    private PhotoListRepository repository;

    public NextPhotoInteractorImpl(PhotoListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String tags) {
        repository.getNextPhoto(tags);
    }
}
