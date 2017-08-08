package com.shellcore.android.myphotolibrary.login;

import com.shellcore.android.myphotolibrary.libs.base.EventBus;
import com.shellcore.android.myphotolibrary.login.events.LoginEvent;
import com.shellcore.android.myphotolibrary.login.ui.LoginView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Cesar on 08/08/2017.
 */

public class LoginPresenterImpl implements LoginPresenter {

    private EventBus eventBus;
    private LoginView view;
    private LoginInteractor interactor;

    public LoginPresenterImpl(EventBus eventBus, LoginView view, LoginInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        view = null;
    }

    @Override
    public void registerNewUser(String user, String pass) {
        if (view != null) {
            view.disableInputs();
            view.showProgressbar();
        }

        interactor.doSignup(user, pass);
    }

    @Override
    public void login(String user, String pass) {
        if (view != null) {
            view.disableInputs();
            view.showProgressbar();
        }

        interactor.doSignin(user, pass);
    }

    @Override
    public void checkForAuthenticatedUser() {
        if (view != null) {
            view.disableInputs();
            view.showProgressbar();
        }
        interactor.checkAlreadyAuthenticated();
    }

    @Override
    @Subscribe
    public void onEventMainthread(LoginEvent event) {
        switch (event.getEventType()) {
            case LoginEvent.ON_SIGNIN_SUCCESS:
                onSinginSuccess();
                break;
            case LoginEvent.ON_SIGNIN_ERROR:
                onSigninError(event.getErrorMessage());
                break;
            case LoginEvent.ON_SIGNUP_SUCCESS:
                onSignupSuccess();
                break;
            case LoginEvent.ON_SIGNUP_ERROR:
                onSignupError(event.getErrorMessage());
                break;
            case LoginEvent.ON_FAILED_TO_RECOVER_SESSION:
                onFailedToRecoverSession();
                break;
        }
    }

    private void onSinginSuccess() {
        if (view != null) {
            view.navigateToMainScreen();
        }
    }

    private void onSignupSuccess() {
        if (view != null) {
            view.newUserSuccess();
        }
    }

    private void onSigninError(String errorMessage) {
        if (view != null) {
            view.hideProgressbar();
            view.enableInputs();
            view.loginError(errorMessage);
        }
    }

    private void onSignupError(String errorMessage) {
        if (view != null) {
            view.hideProgressbar();
            view.enableInputs();
            view.newUserError(errorMessage);
        }
    }

    private void onFailedToRecoverSession() {
        if (view != null) {
            view.hideProgressbar();
            view.enableInputs();
        }
    }
}
