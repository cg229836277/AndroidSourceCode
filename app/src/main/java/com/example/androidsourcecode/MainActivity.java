package com.example.androidsourcecode;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.SingleScheduler;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {
    String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        try {
            GithubService service = retrofit.create(GithubService.class);
            Call<ResponseBody> repos = service.listRepos("cg229836277");
//                    Response<ResponseBody> response = repos.execute();
//                    ResponseBody responseBody = response.body();
//                    String message = responseBody.string();
//                    Log.d(TAG, "message:" + message);

            repos.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response){
                    try {
                        ResponseBody responseBody = response.body();
                        String message = responseBody.string();
                        Log.d(TAG, "message:" + message);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });

            Observable<String> observable = service.listRepos1("cg22983677");
            observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {

                @Override
                public void onSubscribe(Disposable d) {
                    
                }

                @Override
                public void onNext(String s) {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
        } catch (Exception | Error e) {
            e.printStackTrace();
        }
    }
}