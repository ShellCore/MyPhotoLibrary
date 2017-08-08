package com.shellcore.android.myphotolibrary.libs;

import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.shellcore.android.myphotolibrary.libs.base.ImageLoader;

/**
 * Created by Cesar on 07/08/2017.
 */

public class GlideImageLoader implements ImageLoader {

    private RequestManager glideRequestManager;

    public GlideImageLoader(RequestManager glideRequestManager) {
        this.glideRequestManager = glideRequestManager;
    }

    @Override
    public void load(ImageView view, String url) {
        glideRequestManager.load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(view);
    }
}
