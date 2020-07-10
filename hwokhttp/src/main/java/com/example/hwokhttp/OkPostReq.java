package com.example.hwokhttp;

import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Time       : 2020/07/10 下午1:54
 * Author     : sion
 * Description:
 */
public class OkPostReq extends OkReq {
    private final Request.Builder builder;
    private OkHttpClient okHttpClient;

    public OkPostReq(OkHttpClient okHttpClient, String url) {
        this.okHttpClient = okHttpClient;
        builder = new Request.Builder().url(url);

    }

    public void addHeader(Map<String, String> header) {
        for (Map.Entry<String, String> reqHeaders : header.entrySet()) {
            String headerKey = reqHeaders.getKey();
            String headerValue = reqHeaders.getKey();
            builder.addHeader(headerKey,headerValue);
        }
    }


}
