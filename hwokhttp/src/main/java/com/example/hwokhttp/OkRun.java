package com.example.hwokhttp;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.MainThread;

import java.net.PortUnreachableException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;

/**
 * Time       : 2020/07/10 上午11:21
 * Author     : sion
 * Description:
 */
public class OkRun {
    private static OkRun okRun;
    private OkHttpClient okHttpClient;
    private final int CONNECT_TIME_OUT=10;              //连接超时
    private final int READ_TIME_OUT=10;                 //读超时

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
        .readTimeout(READ_TIME_OUT,TimeUnit.SECONDS)
//         .connectionPool()
        .build();

    }

    protected void doRun(Builder builder) {
        if (builder.reqMode == OkMode.GET) {
            //Get请求
        } else {
            //POST请求
        }
    }

    protected void doGet() {

    }

    protected void doPost() {

    }


    public static class Builder {
        private String api;
        private OkMode reqMode;
        private OkMode paramMode;
        private Class type;

        public Builder api(String api) {
            this.api = api;
            return this;
        }

        public Builder reqMode(OkMode reqMode) {
            this.reqMode = reqMode;
            return this;
        }

        public Builder paramMode(OkMode paramMode) {
            this.paramMode = paramMode;
            return this;
        }

        public Builder type(Class type) {
            this.type = type;
            return this;
        }


        public <T> Observable<T> doRun() {
            OkRun.okRun().doRun(this);
            return null;
        }
    }
}
