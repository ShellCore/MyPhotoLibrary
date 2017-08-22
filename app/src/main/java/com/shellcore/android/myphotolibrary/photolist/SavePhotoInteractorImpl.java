package com.shellcore.android.myphotolibrary.photolist;

import com.shellcore.android.myphotolibrary.db.entities.Photo;

/**
 * Created by Cesar on 22/08/2017.
 */

public class SavePhotoInteractorImpl implements SavePhotoInteractor {

    private PhotoListRepository repository;

    public SavePhotoInteractorImpl(PhotoListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Photo photo) {
        repository.savePhoto(photo);
    }
}
