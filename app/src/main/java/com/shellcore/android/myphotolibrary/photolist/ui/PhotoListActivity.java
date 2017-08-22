package com.shellcore.android.myphotolibrary.photolist.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shellcore.android.myphotolibrary.R;

public class PhotoListActivity extends AppCompatActivity {

    public static final String LIST_KEY = "PHOTOS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_list);
    }
}
