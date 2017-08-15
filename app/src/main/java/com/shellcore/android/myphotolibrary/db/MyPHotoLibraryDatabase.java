package com.shellcore.android.myphotolibrary.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Cesar on 08/08/2017.
 */

@Database(name = MyPHotoLibraryDatabase.NAME, version = MyPHotoLibraryDatabase.VERSION)
public class MyPHotoLibraryDatabase {

    public static final String NAME = "photoLibrary";
    public static final int VERSION = 1;
}
