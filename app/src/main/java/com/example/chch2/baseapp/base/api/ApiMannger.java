package com.example.chch2.baseapp.base.api;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;

import com.example.chch2.baseapp.base.bean.BaseEntity;
import com.example.chch2.baseapp.base.constant.HttpUrl;
import com.example.chch2.baseapp.base.sign.Sign;
import com.example.chch2.baseapp.base.util.NetWorkUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

import static com.example.chch2.baseapp.App.context;

public class ApiMannger {
    //baseUrl 协议 主机 端口号
    private static final String ENDPOINT = HttpUrl.ENDPOINT;
    //缓存文件路径
    static File cacheFile = new File(context.getExternalCacheDir(), "WQBCache");
    static Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
    //拦截器
    static Interceptor mInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            if (!NetWorkUtil.isNetWorkConnected(context)) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }

            Response response = chain.proceed(request);

            if (NetWorkUtil.isNetWorkConnected(context)) {
                int maxAge = 0 * 60;
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                // 无网络时，设置超时为4周
                int maxStale = 60 * 60 * 24 * 28;
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
            }
            return response;
        }
    };

    //获取设备唯一ID
    public static String getUid() {
        String DEVICE_ID = "null";//设备唯一的ID
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            DEVICE_ID = tm.getDeviceId();
            if (DEVICE_ID == null || DEVICE_ID.equals("")) {
                DEVICE_ID = "null";
            }
        } catch (Exception e) {
            DEVICE_ID = "null";
        }
        return DEVICE_ID;
    }


    static OkHttpClient mClient = new OkHttpClient.Builder()
            .sslSocketFactory(createSSLSocketFactory())
            .hostnameVerifier(new TrustAllHostnameVerifier())
            //.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .retryOnConnectionFailure(true) //失败重连
            .readTimeout(30 * 1000, TimeUnit.MILLISECONDS)
            .cache(cache)
            .build();



    //2018.7.23---日期转换格式
    private static final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    //构建Retrofit实例
    private static final Retrofit sRetrofit = new Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .client(mClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 使用RxJava作为回调适配器
            .build();


    /**
     * 默认信任所有的证书
     * TODO 最好加上证书认证，主流App都有自己的证书
     *
     * @return
     */
    @SuppressLint("TrulyRandom")
    private static SSLSocketFactory createSSLSocketFactory() {

        SSLSocketFactory sSLSocketFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllManager()},
                    new SecureRandom());
            sSLSocketFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return sSLSocketFactory;
    }

    private static class TrustAllManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)

                throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    //定义网络请求接口
    private static final ApiService apiManager = sRetrofit.create(ApiService.class);



    //2018.7.21---路况信息接口
    public static Observable<BaseEntity<List<JsonObject>>> getHighwayconditon(Map<String, String> params) {
        return apiManager.getHighwayconditon(Sign.headerMap(params), params);
    }
}
