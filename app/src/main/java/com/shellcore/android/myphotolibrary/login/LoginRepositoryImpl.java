package com.shellcore.android.myphotolibrary.login;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.shellcore.android.myphotolibrary.libs.base.EventBus;
import com.shellcore.android.myphotolibrary.login.events.LoginEvent;

/**
 * Created by Cesar on 07/08/2017.
 */

public class LoginRepositoryImpl implements LoginRepository {

    private EventBus eventBus;

    public LoginRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void signin(String user, String pass) {
        if (user != null && pass != null) {
            FirebaseAuth auth = FirebaseAuth.getInstance();
            try {
                auth.signInWithEmailAndPassword(user, pass)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                post(LoginEvent.ON_SIGNIN_SUCCESS);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                post(LoginEvent.ON_SIGNIN_ERROR, e.getLocalizedMessage());
                            }
                        });
            } catch (Exception e) {
                post(LoginEvent.ON_SIGNIN_ERROR, e.getLocalizedMessage());
            }
        }
    }

    @Override
    public void signup(final String user, final String pass) {
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(user, pass)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        post(LoginEvent.ON_SIGNUP_SUCCESS);
                        signin(user, pass);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof FirebaseAuthUserCollisionException) {
                            signin(user, pass);
                        } else {
                            post(LoginEvent.ON_SIGNUP_ERROR, e.getLocalizedMessage());
                        }
                    }
                });
    }

    @Override
    public void checkAlreadyAuthenticated() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            post(LoginEvent.ON_SIGNIN_SUCCESS);
        } else {
            post(LoginEvent.ON_FAILED_TO_RECOVER_SESSION);
        }
    }

    private void post(int eventType, String errorMessage) {
        LoginEvent event = new LoginEvent();
        event.setEventType(eventType);
        event.setErrorMessage(errorMessage);
        eventBus.post(event);
    }

    private void post(int eventType) {
        post(eventType, null);
    }
}
