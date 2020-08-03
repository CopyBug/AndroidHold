package com.example.hwokhttp;

import android.os.Handler;
import android.os.Looper;
import android.util.AndroidException;
import android.util.Log;

import androidx.annotation.MainThread;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.PortUnreachableException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;

/**
 * Time       : 2020/07/10 上午11:21
 * Author     : sion
 * Description:
 */
public class OkRun {
    private static OkRun okRun;
    private OkHttpClient okHttpClient;
    private final int CONNECT_TIME_OUT = 10;              //连接超时
    private final int READ_TIME_OUT = 10;                 //读超时
    private BaseOkReq baseOkReq;

    public static OkRun okRun() {
        if (okRun == null) {
            synchronized (OkRun.class) {
                if (okRun == null) {
                    okRun = new OkRun();
                }
            }
        }
        return okRun;
    }

    public OkRun() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
//         .connectionPool()
                .build();

    }

    protected <T> Observable<T> doRun(Builder builder) {
        if (builder.reqMode == OkMode.GET) {
            //Get请求
            baseOkReq = new OkGetReq<T>(okHttpClient, builder.api);
            baseOkReq.param = builder.baseReqEntity.getReqMap();
            return baseOkReq.<T>doGet();
        } else {
            //POST请求
            baseOkReq = new OkPostReq<T>(okHttpClient, builder.api);
            baseOkReq.param = builder.baseReqEntity.getReqMap();
            switch (builder.reqMode) {
                case POST:
                    return baseOkReq.doPost();
                case POST_BODY:
                    return baseOkReq.doPostFromRx();
                case POST_FORM:
                    return baseOkReq.doPostJsonRx();
                case POST_FILE:
                    return baseOkReq.doPostFile();

            }
        }
        return null;


    }

    public static class Builder {
        private String api;
        private OkMode reqMode;
        private BaseReqEntity baseReqEntity;

        public Builder() {

        }

        public Builder api(String api) {
            this.api = api;
            return this;
        }

        public Builder setBaseReqEntity(BaseReqEntity baseReqEntity) {
            this.baseReqEntity = baseReqEntity;
            return this;
        }

        public Builder paramMode(OkMode paramMode) {
            this.reqMode = paramMode;
            return this;
        }

        public <T> Observable<T> doRun() {

            return OkRun.okRun().<T>doRun(this).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }
    }
}
