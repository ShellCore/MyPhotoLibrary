package com.shellcore.android.myphotolibrary.main.di;

import com.shellcore.android.myphotolibrary.libs.base.EventBus;
import com.shellcore.android.myphotolibrary.libs.base.ImageStorage;
import com.shellcore.android.myphotolibrary.main.MainInteractor;
import com.shellcore.android.myphotolibrary.main.MainInteractorImpl;
import com.shellcore.android.myphotolibrary.main.MainPresenter;
import com.shellcore.android.myphotolibrary.main.MainPresenterImpl;
import com.shellcore.android.myphotolibrary.main.MainRepository;
import com.shellcore.android.myphotolibrary.main.MainRepositoryImpl;
import com.shellcore.android.myphotolibrary.main.ui.MainView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Cesar on 15/08/2017.
 */

@Module
public class MainModule {

    private MainView view;

    public MainModule(MainView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    MainPresenter providesMainPresenter(EventBus eventBus, MainView view, MainInteractor interactor) {
        return new MainPresenterImpl(eventBus, view, interactor);
    }

    @Provides
    @Singleton
    MainView providesMainView() {
        return view;
    }

    @Provides
    @Singleton
    MainInteractor providesMainInteractor(MainRepository repository) {
        return new MainInteractorImpl(repository);
    }

    @Provides
    @Singleton
    MainRepository providesMainRepository(EventBus eventBus, ImageStorage imageStorage) {
        return new MainRepositoryImpl(eventBus, imageStorage);
    }
}
