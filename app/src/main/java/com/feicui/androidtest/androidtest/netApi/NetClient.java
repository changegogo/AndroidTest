package com.feicui.androidtest.androidtest.netApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: dlw on 2016/9/8 15:18
 * Email: dailongshao@126.com
 */
public class NetClient {
    private final String TAG = NetClient.class.getSimpleName();
    private static NetClient netClient;
    private final String BUS_BASE_URL = "http://cloud.bmob.cn";
    private WeatherApi mWeatherApi;


    private NetClient(){
        // 日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BUS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        mWeatherApi = retrofit.create(WeatherApi.class);
    }
    public static synchronized NetClient getInstance(){
        if(netClient == null)
            netClient = new NetClient();
        return netClient;
    }

    public WeatherApi getWeatherApi(){
        return mWeatherApi;
    }
}
