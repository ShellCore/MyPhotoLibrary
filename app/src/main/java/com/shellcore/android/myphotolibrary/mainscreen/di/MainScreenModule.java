package com.shellcore.android.myphotolibrary.mainscreen.di;

import com.shellcore.android.myphotolibrary.libs.base.EventBus;
import com.shellcore.android.myphotolibrary.mainscreen.MainScreenInteractor;
import com.shellcore.android.myphotolibrary.mainscreen.MainScreenInteractorImpl;
import com.shellcore.android.myphotolibrary.mainscreen.MainScreenPresenter;
import com.shellcore.android.myphotolibrary.mainscreen.MainScreenPresenterImpl;
import com.shellcore.android.myphotolibrary.mainscreen.MainScreenRepository;
import com.shellcore.android.myphotolibrary.mainscreen.MainScreenRepositoryImpl;
import com.shellcore.android.myphotolibrary.mainscreen.ui.MainScreenView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Cesar on 17/08/2017.
 */

@Module
public class MainScreenModule {

    private MainScreenView view;

    public MainScreenModule(MainScreenView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    MainScreenPresenter providesMainScreenPresenter(EventBus eventBus, MainScreenView view, MainScreenInteractor interactor) {
        return new MainScreenPresenterImpl(eventBus, view, interactor);
    }

    @Provides
    @Singleton
    MainScreenView providesMainScreenView() {
        return view;
    }

    @Provides
    @Singleton
    MainScreenInteractor providesMainScreenInteractor(MainScreenRepository repository) {
        return new MainScreenInteractorImpl(repository);
    }

    @Provides
    @Singleton
    MainScreenRepository providesMainScreenRepository(EventBus eventBus) {
        return new MainScreenRepositoryImpl(eventBus);
    }
}
