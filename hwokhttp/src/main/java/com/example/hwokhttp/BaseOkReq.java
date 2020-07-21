package com.example.hwokhttp;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Time       : 2020/07/10 下午1:54
 * Author     : sion
 * Description:
 */
public abstract class BaseOkReq<T> implements BaseOk<T>{
    protected final Request.Builder builder;
    protected Map<String, Object> param;
    protected OkHttpClient okHttpClient;
    protected String reqUrl;
    public BaseOk<T> baseOk;
    public BaseOkReq(OkHttpClient okHttpClient,String reqUrl) {
        this.okHttpClient = okHttpClient;
        builder = new Request.Builder();
        param = new HashMap<>();
        setReqUrl(reqUrl);
        baseOk=this;
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
    public <T> Observable<T> doPostJsonRx(){
        return null;
    }
    public <T> Observable<T> doPostFromRx() {
        return null;
    }

    public <T> Observable<T> doPost() {
        return null;
    }

    public <T> Observable<T> doPostFile(){
        return null;
    }

    public <T> Observable<T> doGet() {
        return null;
    }
}
