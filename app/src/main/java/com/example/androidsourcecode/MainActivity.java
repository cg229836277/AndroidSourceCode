package com.example.androidsourcecode;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
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
//            Call<ResponseBody> repos = service.listRepos("cg229836277");
//                    Response<ResponseBody> response = repos.execute();
//                    ResponseBody responseBody = response.body();
//                    String message = responseBody.string();
//                    Log.d(TAG, "message:" + message);

//            repos.enqueue(new Callback<ResponseBody>() {
//                @Override
//                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                    try {
//                        ResponseBody responseBody = response.body();
//                        if (responseBody == null || !response.isSuccessful()) {
//                            String errorBody = response.errorBody().string();
//                            Log.e(TAG, "errorBody message:" + errorBody);
//                            return;
//                        }
//                        String message = responseBody.string();
//                        Log.e(TAG, "message:" + message);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponseBody> call, Throwable t) {
//                    Log.e(TAG, "onFailure:" + t.getMessage());
//                }
//            });

            Observable<String> observable = service.listRepos1("cg22983677");
//            observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
//                @Override
//                public void accept(String s) throws Exception {
//                    Log.e(TAG, "accept result:" + s);
//                }
//            }, new Consumer<Throwable>() {
//                @Override
//                public void accept(Throwable throwable) throws Exception {
//                    Log.e(TAG, "accept throwable:" + throwable.getMessage());
//                }
//            });


//            observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
//                @Override
//                public void onSubscribe(Disposable d) {
//                    Log.e(TAG, "onSubscribe:" + d);
//                }
//
//                @Override
//                public void onNext(String s) {
//                    Log.e(TAG, "onNext:" + s);
//                }
//
//                @Override
//                public void onError(Throwable e) {
//                    Log.e(TAG, "onError:" + e.getMessage());
//                }
//
//                @Override
//                public void onComplete() {
//                    Log.e(TAG, "onComplete");
//                }
//            });

            Disposable disposable = observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(new Function<String, String>() {
                        @Override
                        public String apply(String rst) throws Exception {
                            return rst;
                        }
                    })
                    .subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Exception {

                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {

                        }
                    });

            Disposable d = Flowable.just("Hello world!")
                    .delay(1, TimeUnit.SECONDS)
                    .subscribeWith(new DisposableSubscriber<String>() {
                        @Override
                        public void onStart() {
                            Log.e(TAG, "Start!");
                            request(1);
                        }

                        @Override
                        public void onNext(String t) {
                            Log.e(TAG, t);
                            request(1);
                        }

                        @Override
                        public void onError(Throwable t) {
                            t.printStackTrace();
                        }

                        @Override
                        public void onComplete() {
                            Log.e(TAG, "Done!");
                        }
                    });
            Thread.sleep(1500);
            d.dispose();
        } catch (Exception | Error e) {
            e.printStackTrace();
        }
    }
}