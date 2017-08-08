package com.shellcore.android.myphotolibrary.login.di;

import com.shellcore.android.myphotolibrary.libs.base.EventBus;
import com.shellcore.android.myphotolibrary.login.LoginInteractor;
import com.shellcore.android.myphotolibrary.login.LoginInteractorImpl;
import com.shellcore.android.myphotolibrary.login.LoginPresenter;
import com.shellcore.android.myphotolibrary.login.LoginPresenterImpl;
import com.shellcore.android.myphotolibrary.login.LoginRepository;
import com.shellcore.android.myphotolibrary.login.LoginRepositoryImpl;
import com.shellcore.android.myphotolibrary.login.ui.LoginView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Cesar on 08/08/2017.
 */

@Module
public class LoginModule {

    private LoginView view;

    public LoginModule(LoginView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    LoginPresenter providesLoginPresenter(EventBus eventBus, LoginView view, LoginInteractor interactor) {
        return new LoginPresenterImpl(eventBus, view, interactor);
    }

    @Provides
    @Singleton
    LoginView providesLoginView() {
        return view;
    }

    @Provides
    @Singleton
    LoginInteractor providesLoginInteractor(LoginRepository repository) {
        return new LoginInteractorImpl(repository);
    }

    @Provides
    @Singleton
    LoginRepository providesLoginRepository(EventBus eventBus) {
        return new LoginRepositoryImpl(eventBus);
    }
}
