package com.example.androidsourcecode;

import io.reactivex.Observable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubService {
    @GET("users/{user}/repos")
    Call<ResponseBody> listRepos(@Path("user") String user);

    @GET("users/{user}/repos")
    Observable<String> listRepos1(@Path("user") String user);

    @GET("users/{user}")
    Observable<UserInfoBean> getUserInfo(@Path("user") String user);

    @GET("users/{user}")
    Observable<Result> getUserInfo1(@Path("user") String user);

    @GET("users/{user}")
    Observable<Response> getUserInfo2(@Path("user") String user);

    @GET("users/{user}")
    Flowable<Response> getUserInfo3(@Path("user") String user);

    @GET("users/{user}")
    Single<Response> getUserInfo4(@Path("user") String user);

    @GET("users/{user}")
    Maybe<Response> getUserInfo5(@Path("user") String user);
}
