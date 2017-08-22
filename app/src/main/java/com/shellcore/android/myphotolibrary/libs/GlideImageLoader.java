package com.shellcore.android.myphotolibrary.libs;

import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.shellcore.android.myphotolibrary.libs.base.ImageLoader;

/**
 * Created by Cesar on 07/08/2017.
 */

public class GlideImageLoader implements ImageLoader {

    private RequestManager glideRequestManager;
    private RequestListener onFinishedLoadingListener;

    public GlideImageLoader(RequestManager glideRequestManager) {
        this.glideRequestManager = glideRequestManager;
    }

    @Override
    public void load(ImageView view, String url) {
        if (onFinishedLoadingListener != null) {
            glideRequestManager.load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .listener(onFinishedLoadingListener)
                    .into(view);
        } else {
            glideRequestManager.load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(view);
        }

    }

    @Override
    public void setOnFinishedImageLoaderListener(Object listener) {
        if (listener instanceof RequestListener) {
            this.onFinishedLoadingListener = (RequestListener) listener;
        }
    }
}
