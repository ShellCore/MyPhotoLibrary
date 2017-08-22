package com.shellcore.android.myphotolibrary.photolist.ui;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.shellcore.android.myphotolibrary.MyPhotoLibraryApp;
import com.shellcore.android.myphotolibrary.R;
import com.shellcore.android.myphotolibrary.db.entities.Photo;
import com.shellcore.android.myphotolibrary.libs.base.ImageLoader;
import com.shellcore.android.myphotolibrary.photolist.PhotoListPresenter;
import com.shellcore.android.myphotolibrary.photolist.di.PhotoListComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoListActivity extends AppCompatActivity implements PhotoListView, SwipeGestureListener {

    // Constants
    public static final String TAGS = "TAGS";

    // Variables
    private String tags;
    private Photo currentPhoto;

    // Services
    @Inject
    PhotoListPresenter presenter;
    @Inject
    ImageLoader imageLoader;

    // Components
    @BindView(R.id.img_flickr_photo)
    ImageView imgFlickrPhoto;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    @BindView(R.id.container)
    CoordinatorLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_list);
        ButterKnife.bind(this);

        setupInjection();
        setupImageLoader();
        setupGestureDetection();
        presenter.onCreate();
        loadPhotosFromBundle();
        presenter.getNextPhoto(tags);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgressBar() {
        progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressbar.setVisibility(View.GONE);
    }

    @Override
    public void saveAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.save_animation);
        anim.setAnimationListener(getAnimationListener());
        imgFlickrPhoto.startAnimation(anim);
    }

    @Override
    public void dismissAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dismiss_animation);
        anim.setAnimationListener(getAnimationListener());
        imgFlickrPhoto.startAnimation(anim);
    }

    @Override
    public void onPhotoSaved() {
        Snackbar.make(container, R.string.photolist_notice_saved, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void setPhoto(Photo photo) {
        currentPhoto = photo;
        imageLoader.load(imgFlickrPhoto, photo.getUrl());
    }

    @Override
    public void onGetPhotoError(String error) {
        String errorMsg = String.format(getString(R.string.photolist_error), error);
        Snackbar.make(container, errorMsg, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onKeep() {
        if (currentPhoto != null) {
            presenter.savePhoto(currentPhoto);
        }
    }

    @Override
    public void onDismiss() {
        presenter.dismissPhoto();
    }

    private void setupInjection() {
        MyPhotoLibraryApp app = (MyPhotoLibraryApp) getApplication();
        PhotoListComponent component = app.getPhotoListComponent(this, this);
        component.inject(this);
    }

    private void setupImageLoader() {
        RequestListener glideRequestListener = new RequestListener() {
            @Override
            public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
                presenter.imageError(e.getLocalizedMessage());
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
                presenter.imageReady();
                return false;
            }
        };
        imageLoader.setOnFinishedImageLoaderListener(glideRequestListener);
    }

    private void setupGestureDetection() {
        final GestureDetector gestureDetector = new GestureDetector(this, new SwipeGestureDetector(this));
        View.OnTouchListener gestureOnTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };
    }

    private Animation.AnimationListener getAnimationListener() {
        return new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                clearImage();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        };
    }

    private void loadPhotosFromBundle() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            tags = (String) extras.getSerializable(TAGS);
        }
    }

    private void clearImage() {
        imgFlickrPhoto.setImageResource(0);
    }
}
