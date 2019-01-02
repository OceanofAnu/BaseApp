package com.example.chch2.baseapp.base.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/** 基础Interceptor 设置固定参数
 * Created by gyg on 2016/8/28.
 */
public class BaseIntercepter implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            //加入请求头的信息
            Request request = chain.request();
            request.newBuilder().addHeader("token","xxxx").build();
            Response response = chain.proceed(request);
            return response;
        }
    }