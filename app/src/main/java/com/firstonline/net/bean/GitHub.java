package com.firstonline.net.bean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public interface GitHub {

    @GET("/repos/{owner}/{repo}/contributors")
    Call<String> contributors(
            @Path("owner") String owner,
            @Path("repo") String repo);
}
