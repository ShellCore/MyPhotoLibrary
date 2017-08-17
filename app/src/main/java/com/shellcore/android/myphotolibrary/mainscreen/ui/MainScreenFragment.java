package com.shellcore.android.myphotolibrary.mainscreen.ui;


import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.shellcore.android.myphotolibrary.MyPhotoLibraryApp;
import com.shellcore.android.myphotolibrary.R;
import com.shellcore.android.myphotolibrary.db.entities.Photo;
import com.shellcore.android.myphotolibrary.libs.base.ImageLoader;
import com.shellcore.android.myphotolibrary.mainscreen.MainScreenPresenter;
import com.shellcore.android.myphotolibrary.mainscreen.di.MainScreenComponent;

import java.io.ByteArrayOutputStream;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainScreenFragment extends Fragment implements MainScreenView {

    private Photo lastPhoto;

    // Services
    @Inject
    ImageLoader loader;
    @Inject
    MainScreenPresenter presenter;

    // Components
    @BindView(R.id.img_last_photo)
    ImageView imgLastPhoto;
    @BindView(R.id.txt_no_photos)
    TextView txtNoPhotos;
    @BindView(R.id.btn_share)
    FloatingActionButton btnShare;
    @BindView(R.id.container)
    CoordinatorLayout container;
    Unbinder unbinder;

    public MainScreenFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_screen, container, false);
        unbinder = ButterKnife.bind(this, v);

        setupInjection();
        presenter.onCreate();
        presenter.readLastPhoto();
        return v;
    }

    @Override
    public void onDestroyView() {
        presenter.onDestroy();
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showImage() {
        imgLastPhoto.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideImage() {
        imgLastPhoto.setVisibility(View.GONE);
    }

    @Override
    public void showTxtNoPhotos() {
        txtNoPhotos.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideTxtNoPhotos() {
        txtNoPhotos.setVisibility(View.GONE);
    }

    @Override
    public void setLastPhoto(Photo photo) {
        this.lastPhoto = photo;
        loader.load(imgLastPhoto, photo.getUrl());

    }

    @Override
    public void setErrorMessage(String errorMessage) {
        Snackbar.make(container, errorMessage, Snackbar.LENGTH_SHORT)
                .show();
    }

    @OnClick(R.id.btn_share)
    @Override
    public void handleBtnShare() {
        Bitmap bitmap = ((GlideBitmapDrawable) imgLastPhoto.getDrawable()).getBitmap();
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images
                .Media
                .insertImage(getActivity().getContentResolver(), bitmap, null, null);
        Uri imageUri = Uri.parse(path);

        share.putExtra(Intent.EXTRA_STREAM, imageUri);
        startActivity(Intent.createChooser(share, getString(R.string.share)));
    }

    private void setupInjection() {
        MyPhotoLibraryApp app = (MyPhotoLibraryApp) getActivity().getApplication();
        MainScreenComponent component = app.getMainScreenComponent(getActivity(), this);
        component.inject(this);
    }
}
