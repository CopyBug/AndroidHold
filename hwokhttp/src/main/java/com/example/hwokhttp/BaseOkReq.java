package com.example.hwokhttp;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Time       : 2020/07/10 下午1:54
 * Author     : sion
 * Description:
 */
public abstract class BaseOkReq<T> implements BaseOk<T> {
    protected final Request.Builder builder;
    protected Map<String, Object> param;
    protected OkHttpClient okHttpClient;
    protected String reqUrl;
    public BaseOk<T> baseOk;

    public BaseOkReq(OkHttpClient okHttpClient, String reqUrl) {
        this.okHttpClient = okHttpClient;
        builder = new Request.Builder();
        param = new HashMap<>();
        setReqUrl(reqUrl);
        baseOk = this;
    }

    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl;
        builder.url(this.reqUrl);
    }

    public void addHeader(Map<String, String> header) {
        for (Map.Entry<String, String> reqHeaders : header.entrySet()) {
            String headerKey = reqHeaders.getKey();
            String headerValue = reqHeaders.getKey();
            builder.addHeader(headerKey, headerValue);
        }
    }

    public BaseOkReq param(String key, String value) {
        param.put(key, value);
        return this;
    }

    public <T> Observable<T> doPostJsonRx() {
        return null;
    }

    public <T> Observable<T> doPostFromRx() {
        return null;
    }

    public <T> Observable<T> doPost() {
        return null;
    }

    public <T> Observable<T> doPostFile() {
        return null;
    }

    public <T> Observable<T> doGet() {
        return null;
    }

    public <T>Callback getCallBack(final ObservableEmitter<T> emitter) {
        return new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                emitter.onError(e);
                emitter.onComplete();
            }

            @Override
            public void onResponse(Call call, Response response)  {
                try {
                    String json = response.body().string();
                    emitter.onNext((T) new Gson().fromJson(json, HwNetManager.getHwNetManager().getClass(reqUrl)));
                    emitter.onComplete();
                } catch (IOException e) {
                    e.printStackTrace();
                    emitter.onError(new Throwable(e.getMessage() + ""));
                    emitter.onComplete();
                }
            }
        };
    }
}
