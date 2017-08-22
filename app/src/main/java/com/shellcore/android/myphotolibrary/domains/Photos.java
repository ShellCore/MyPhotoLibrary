package com.shellcore.android.myphotolibrary.domains;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shell on 21/08/2017.
 */

public class Photos {

    private int page;
    private int pages;
    private int perPage;
    private long total;
    private List<Photo> photo = new ArrayList<>();
}
