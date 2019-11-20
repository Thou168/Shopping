package com.bokor.shopping.App.sliderimage;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Slider {
    // Block to dynamic url
    private static final String BASE_URL = "http://bgashare.bingoogolapple.cn/banner/api/";
    private static Retrofit retrofit = null;
    static OkHttpClient httpClient=new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build();
    public static Retrofit getClient(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient).build();
        }
        return retrofit;
    }
}