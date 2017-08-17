package com.shellcore.android.myphotolibrary.login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.shellcore.android.myphotolibrary.MyPhotoLibraryApp;
import com.shellcore.android.myphotolibrary.R;
import com.shellcore.android.myphotolibrary.login.LoginPresenter;
import com.shellcore.android.myphotolibrary.login.di.LoginComponent;
import com.shellcore.android.myphotolibrary.main.ui.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {

    // Services
    @Inject
    LoginPresenter presenter;

    // Components
    @BindView(R.id.container)
    RelativeLayout container;
    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.til_email)
    TextInputLayout tilEmail;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.til_password)
    TextInputLayout tilPassword;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.progressbarmaincsreen)
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        setupInjection();

        presenter.onCreate();
        presenter.checkForAuthenticatedUser();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void enableInputs() {
        setInputs(true);
    }

    @Override
    public void disableInputs() {
        setInputs(false);
    }

    @Override
    public void showProgressbar() {
        progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressbar() {
        progressbar.setVisibility(View.GONE);
    }

    @Override
    @OnClick(R.id.btn_register)
    public void handleSignInUp() {
        presenter.registerNewUser(edtEmail.getText().toString(), edtPassword.getText().toString());
    }

    @Override
    public void navigateToMainScreen() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void loginError(String error) {
        edtPassword.setText("");
        String errorMessage = String.format(getString(R.string.login_message_error_signin), error);
        edtPassword.setError(errorMessage);
    }

    @Override
    public void newUserError(String error) {
        edtPassword.setText("");
        String errorMessage = String.format(getString(R.string.login_message_error_signup), error);
        edtPassword.setError(errorMessage);
    }

    @Override
    public void newUserSuccess() {
        Snackbar.make(container, R.string.login_message_success_signup, Snackbar.LENGTH_SHORT)
                .show();
    }

    private void setupInjection() {
        MyPhotoLibraryApp app = (MyPhotoLibraryApp) getApplication();
        LoginComponent component = app.getLoginComponent(this, this);
        component.inject(this);
    }

    private void setInputs(boolean enabled) {
        tilEmail.setEnabled(enabled);
        tilPassword.setEnabled(enabled);
        btnRegister.setEnabled(enabled);
    }
}
