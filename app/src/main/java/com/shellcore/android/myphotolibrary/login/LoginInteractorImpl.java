package com.shellcore.android.myphotolibrary.login;

/**
 * Created by Cesar on 08/08/2017.
 */

public class LoginInteractorImpl implements LoginInteractor {

    private LoginRepository repository;

    public LoginInteractorImpl(LoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public void doSignin(String user, String pass) {
        repository.signin(user, pass);
    }

    @Override
    public void doSignup(String user, String pass) {
        repository.signup(user, pass);
    }

    @Override
    public void checkAlreadyAuthenticated() {
        repository.checkAlreadyAuthenticated();
    }
}
