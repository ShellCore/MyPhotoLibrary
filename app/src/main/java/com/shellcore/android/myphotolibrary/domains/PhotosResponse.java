package com.shellcore.android.myphotolibrary.domains;

/**
 * Created by Shell on 21/08/2017.
 */

public class PhotosResponse {

    private Photos photos;
    private String stat;

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
