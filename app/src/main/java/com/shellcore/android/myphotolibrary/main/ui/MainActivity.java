package com.shellcore.android.myphotolibrary.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.shellcore.android.myphotolibrary.MyPhotoLibraryApp;
import com.shellcore.android.myphotolibrary.R;
import com.shellcore.android.myphotolibrary.login.ui.LoginActivity;
import com.shellcore.android.myphotolibrary.main.MainPresenter;
import com.shellcore.android.myphotolibrary.main.di.MainComponent;
import com.shellcore.android.myphotolibrary.mainscreen.MainScreenFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainView {

    @Inject
    MainPresenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.frm_content)
    FrameLayout relContent;
    @BindView(R.id.share)
    FloatingActionButton share;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.container)
    CoordinatorLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupInjection();
        setupView();

        presenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings :
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_main_screen:
                handleMainScreen();
                break;
            case R.id.nav_camera:
                handleMainScreen();
                break;
            case R.id.nav_gallery:
                handleMyGallery();
                break;
            case R.id.nav_slideshow:
                handleInspectPhotos();
                break;
            case R.id.nav_share:
                handleShare();
                break;
            case R.id.nav_logout:
                handleLogout();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick(R.id.share)
    @Override
    public void handleBtnShare() {
        Snackbar.make(container, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void handleMainScreen() {
        MainScreenFragment fragment = new MainScreenFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.frm_content, fragment);
    }

    @Override
    public void handleCamera() {

    }

    @Override
    public void handleMyGallery() {

    }

    @Override
    public void handleInspectPhotos() {

    }

    @Override
    public void handleShare() {

    }

    @Override
    public void handleLogout() {
        presenter.logout();
    }

    @Override
    public void navigateToLoginView() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void setupInjection() {
        MyPhotoLibraryApp app = (MyPhotoLibraryApp) getApplication();
        MainComponent component = app.getMainComponent(this, this);
        component.inject(this);
    }

    private void setupView() {
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navView.setNavigationItemSelectedListener(this);
    }
}
