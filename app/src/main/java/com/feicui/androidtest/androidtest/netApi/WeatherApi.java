package com.feicui.androidtest.androidtest.netApi;

import com.feicui.androidtest.androidtest.entity.PictureData;
import com.feicui.androidtest.androidtest.entity.WeatherData;
import com.feicui.androidtest.androidtest.entity.WeekData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Author: dlw on 2016/9/8 14:53
 * Email: dailongshao@126.com
 */
//http://cloud.bmob.cn/6112dd9a882286d2/androidtest1?uuid=sdfsfdsfdfdf
public interface WeatherApi {
    @GET("/6112dd9a882286d2/androidtest1")
    Call<PictureData> getPictureData(@Query("uuid") String uuid);

    @GET("/6112dd9a882286d2/androidtest2")
    Call<WeekData> getWeekData(@Query("uuid") String uuid);

    @GET("/6112dd9a882286d2/androidtest3")
    Call<WeatherData> getWeatherData(@Query("uuid") String uuid);

}
