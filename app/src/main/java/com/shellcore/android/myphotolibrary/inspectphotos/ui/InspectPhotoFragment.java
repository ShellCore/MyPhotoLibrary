package com.shellcore.android.myphotolibrary.inspectphotos.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.shellcore.android.myphotolibrary.BaseFragment;
import com.shellcore.android.myphotolibrary.R;
import com.shellcore.android.myphotolibrary.photolist.ui.PhotoListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class InspectPhotoFragment extends BaseFragment implements InspectPhotoView {

    @BindView(R.id.edt_tags)
    EditText edtTags;
    @BindView(R.id.til_search)
    TextInputLayout tilSearch;
    @BindView(R.id.btn_search)
    Button btnSearch;
    @BindView(R.id.container)
    CoordinatorLayout container;

    public InspectPhotoFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_inspect_photo, container, false);
        ButterKnife.bind(this, v);

        return v;
    }

    @OnClick(R.id.btn_search)
    @Override
    public void handleBtnSearch() {
        String tags = edtTags.getText().toString();
        if (!tags.isEmpty()) {
            navigateToPhotoListView(tags);
        }
    }

    @Override
    public void navigateToPhotoListView(String tags) {
        Intent intent = new Intent(this.getActivity(), PhotoListActivity.class);
        intent.putExtra(PhotoListActivity.TAGS, tags);
        startActivity(intent);
    }

    @Override
    public void updatePhoto() {
    }
}
