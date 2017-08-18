package com.shellcore.android.myphotolibrary.photoDetail.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shellcore.android.myphotolibrary.R;
import com.shellcore.android.myphotolibrary.db.entities.Photo;
import com.shellcore.android.myphotolibrary.libs.GlideImageLoader;
import com.shellcore.android.myphotolibrary.libs.base.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoDetailActivity extends AppCompatActivity implements PhotoDetailView {

    // Constants
    public static final String PHOTO = "PHOTO";

    // Variables
    private Photo photo;

    // Services
    ImageLoader imageLoader;

    // Components
    @BindView(R.id.img_photo)
    ImageView imgPhoto;
    @BindView(R.id.txt_title)
    TextView txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);
        ButterKnife.bind(this);

        setupInjection();

        getPhoto();
    }

    private void getPhoto() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            photo = (Photo) extras.getSerializable(PHOTO);
            setImage(photo.getUrl());
            if (photo.getTitle() != null && !photo.getTitle().isEmpty()) {
                showTitle();
                setTitle(photo.getTitle());
            } else {
                hideTitle();
            }
        }
    }

    @Override
    public void showTitle() {
        txtTitle.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideTitle() {
        txtTitle.setVisibility(View.GONE);
    }

    @Override
    public void setImage(String uri) {
        imageLoader.load(imgPhoto, uri);
    }

    @Override
    public void setTitle(String title) {
        txtTitle.setText(title);
    }

    private void setupInjection() {
        imageLoader = new GlideImageLoader(Glide.with(this));
    }
}
