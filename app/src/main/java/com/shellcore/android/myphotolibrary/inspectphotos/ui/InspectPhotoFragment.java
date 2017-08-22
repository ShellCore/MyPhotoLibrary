package com.shellcore.android.myphotolibrary.inspectphotos.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.shellcore.android.myphotolibrary.BaseFragment;
import com.shellcore.android.myphotolibrary.MyPhotoLibraryApp;
import com.shellcore.android.myphotolibrary.R;
import com.shellcore.android.myphotolibrary.db.entities.Photo;
import com.shellcore.android.myphotolibrary.inspectphotos.InspectPhotoPresenter;
import com.shellcore.android.myphotolibrary.inspectphotos.di.InspectPhotoComponent;
import com.shellcore.android.myphotolibrary.photolist.ui.PhotoListActivity;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class InspectPhotoFragment extends BaseFragment implements InspectPhotoView {

    // Services
    @Inject
    InspectPhotoPresenter presenter;

    @BindView(R.id.edt_tags)
    EditText edtTags;
    @BindView(R.id.til_search)
    TextInputLayout tilSearch;
    @BindView(R.id.btn_search)
    Button btnSearch;
    @BindView(R.id.container)
    CoordinatorLayout container;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    public InspectPhotoFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_inspect_photo, container, false);
        ButterKnife.bind(this, v);

        setupInjection();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
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
    public void enableInputs() {
        setInputsEnabled(true);
    }

    @Override
    public void disableInputs() {
        setInputsEnabled(false);
    }

    @Override
    public void showProgressBar() {
        progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressbar.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_search)
    @Override
    public void handleBtnSearch() {
        String tags = edtTags.getText().toString();
        presenter.searchImages(tags);
    }

    @Override
    public void navigateToPhotoListView(List<Photo> photos) {
        Intent intent = new Intent(this.getActivity(), PhotoListActivity.class);
        intent.putExtra(PhotoListActivity.LIST_KEY, (Serializable) photos);
        startActivity(intent);
    }

    @Override
    public void showMessage(String errorMessage) {
        Snackbar.make(container, errorMessage, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void updatePhoto() {
    }

    private void setupInjection() {
        MyPhotoLibraryApp app = (MyPhotoLibraryApp) getActivity().getApplication();
        InspectPhotoComponent component = app.getInspectPhotoComponent(getActivity(), this);
        component.inject(this);
    }

    private void setInputsEnabled(boolean enabled) {
        tilSearch.setEnabled(enabled);
        btnSearch.setEnabled(enabled);
    }
}
