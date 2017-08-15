package com.shellcore.android.myphotolibrary.libs.base;

import java.io.File;

/**
 * Created by Cesar on 14/08/2017.
 */

public interface ImageStorage {
    String getImageUrl(String id);
    void upload(File file, String id, ImageStorageFinishedListener listener);
}
