package com.shellcore.android.myphotolibrary.db.entities;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.shellcore.android.myphotolibrary.db.MyPHotoLibraryDatabase;

/**
 * Created by Cesar on 08/08/2017.
 */

@Table(database = MyPHotoLibraryDatabase.class)
public class Photo extends BaseModel {

    @PrimaryKey
    private long id;

    @Column
    private String url;

    @Column
    private String title;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
