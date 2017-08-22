package com.shellcore.android.myphotolibrary.domains;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Shell on 21/08/2017.
 */

public interface FlickrService {

    @GET("/services/rest/?method=flickr.photos.search")
    Call<PhotosResponse> search(@Query("tags") String tags,
                                @Query("per_page") int perPage);
}
