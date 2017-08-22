package com.shellcore.android.myphotolibrary.photolist;

import com.shellcore.android.myphotolibrary.db.entities.Photo;

/**
 * Created by Cesar on 22/08/2017.
 */

public interface SavePhotoInteractor {

    void execute(Photo photo);
}
