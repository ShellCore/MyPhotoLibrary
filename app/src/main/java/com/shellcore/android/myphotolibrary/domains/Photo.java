package com.shellcore.android.myphotolibrary.domains;

/**
 * Created by Shell on 21/08/2017.
 */

public class Photo {

    private String id;
    private String title;
    private String server;
    private String secret;
    private int farm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public int getFarm() {
        return farm;
    }

    public void setFarm(int farm) {
        this.farm = farm;
    }

    public String getFlickrUrl() {
        return "https://farm" + farm + ".staticflickr.com/" + server + "/" + id + "_" + secret + "_b.jpg";
    }
}
