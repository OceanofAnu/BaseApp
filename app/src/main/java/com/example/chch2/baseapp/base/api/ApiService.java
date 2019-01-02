package com.example.chch2.baseapp.base.api;

import com.example.chch2.baseapp.Entity.WeatherEntity;
import com.example.chch2.baseapp.base.bean.BaseEntity;
import com.example.chch2.baseapp.base.constant.HttpUrl;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface ApiService {
    @GET("weather_mini")
    Observable<WeatherEntity> getMessage(@Query("city") String city);
    //2018.7.21---路况信息接口
    @POST(HttpUrl.QUERYHIGHWAYCONDITION_HIGHWAYCONDITON)
    Observable<BaseEntity<List<JsonObject>>> getHighwayconditon(@HeaderMap Map<String, String> headMap, @QueryMap Map<String, String> params);
}
