package com.example.androidsourcecode;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubService {
    @GET("users/{user}/repos")
    Call<ResponseBody> listRepos(@Path("user") String user);

    @GET("users/{user}/repos")
    Observable<String> listRepos1(@Path("user") String user);

//    @GET("users/{user}")
//    Call<UserInfoBean> getUserInfo(@Path("user") String user);
}
