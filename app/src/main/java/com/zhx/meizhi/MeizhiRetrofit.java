package com.zhx.meizhi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class MeizhiRetrofit {

    final MeizhiService mService;
    MeizhiRetrofit() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/data/福利/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mService = retrofit.create(MeizhiService.class);
    }

    public MeizhiService getService(){
        return mService;
    }
}
