package com.shellcore.android.myphotolibrary.mainscreen;

/**
 * Created by Cesar on 17/08/2017.
 */

public class MainScreenInteractorImpl implements MainScreenInteractor {

    private MainScreenRepository repository;

    public MainScreenInteractorImpl(MainScreenRepository repository) {
        this.repository = repository;
    }

    @Override
    public void readLastPhoto() {
        repository.readLastPhoto();
    }
}
