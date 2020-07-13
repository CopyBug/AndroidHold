package com.example.hwokhttp;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Time       : 2020/07/10 下午1:54
 * Author     : sion
 * Description:
 */
public abstract class OkReq<T> {
    protected  T t;
    protected final Request.Builder builder;
    protected Map<String, String> param;
    protected OkHttpClient okHttpClient;
    protected String reqUrl;

    public OkReq(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
        builder = new Request.Builder();
        param = new HashMap<>();
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

    public OkReq param(String key, String value) {
        param.put(key, value);
        return this;
    }

}
