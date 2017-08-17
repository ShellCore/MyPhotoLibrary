package com.shellcore.android.myphotolibrary.main;

/**
 * Created by Cesar on 14/08/2017.
 */

public class MainInteractorImpl implements MainInteractor {

    private MainRepository repository;

    public MainInteractorImpl(MainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void logout() {
        repository.logout();
    }

    @Override
    public void uploadPhoto(String uri) {
        repository.uploadPhoto(uri);
    }
}
