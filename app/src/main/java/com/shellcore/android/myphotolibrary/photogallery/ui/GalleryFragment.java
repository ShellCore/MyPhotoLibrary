package com.shellcore.android.myphotolibrary.photogallery.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shellcore.android.myphotolibrary.BaseFragment;
import com.shellcore.android.myphotolibrary.MyPhotoLibraryApp;
import com.shellcore.android.myphotolibrary.R;
import com.shellcore.android.myphotolibrary.db.entities.Photo;
import com.shellcore.android.myphotolibrary.photogallery.GalleryPresenter;
import com.shellcore.android.myphotolibrary.photogallery.adapters.GalleryAdapter;
import com.shellcore.android.myphotolibrary.photogallery.adapters.OnItemClickListener;
import com.shellcore.android.myphotolibrary.photogallery.di.GalleryComponent;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends BaseFragment implements GalleryView, OnItemClickListener {

    // Variables
    @Inject
    GalleryAdapter adapter;

    // Services
    @Inject
    GalleryPresenter presenter;

    // Components
    @BindView(R.id.rec_gallery_list)
    RecyclerView recGalleryList;
    @BindView(R.id.txt_no_photos)
    TextView txtNoPhotos;
    @BindView(R.id.container)
    CoordinatorLayout container;

    public GalleryFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gallery, container, false);
        ButterKnife.bind(this, v);

        setupInjection();
        setupRecyclerView();
        presenter.onResume();
        presenter.getPhotos();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onItemClick(Photo photo) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(photo.getUrl()));
        startActivity(intent);
    }

    @Override
    public void showGalleryList() {
        recGalleryList.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideGalleryList() {
        recGalleryList.setVisibility(View.GONE);
    }

    @Override
    public void showMessageNoPhotos() {
        txtNoPhotos.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideMessageNoPhotos() {
        txtNoPhotos.setVisibility(View.GONE);
    }

    @Override
    public void updatePhoto() {
        presenter.getPhotos();
    }

    @Override
    public void showMessage(String msg) {
        Snackbar.make(container, msg, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void setContent(List<Photo> list) {
        adapter.setList(list);
    }

    private void setupInjection() {
        MyPhotoLibraryApp app = (MyPhotoLibraryApp) getActivity().getApplication();
        GalleryComponent component = app.getGalleryComponent(getActivity(), this, this);
        component.inject(this);
    }

    private void setupRecyclerView() {
        recGalleryList.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recGalleryList.setAdapter(adapter);
    }
}
