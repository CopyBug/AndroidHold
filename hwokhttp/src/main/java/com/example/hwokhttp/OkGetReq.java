package com.example.hwokhttp;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.OkHttpClient;


/**
 * Time       : 2020/07/10 下午1:54
 * Author     : sion
 * Description:
 */
public class OkGetReq<T> extends BaseOkReq<T> {

    public OkGetReq(OkHttpClient okHttpClient, String reqUrl) {
        super(okHttpClient, reqUrl);


    }

    @Override
    public <T> Observable<T> doGet() {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) {
                get(emitter);
            }
        });
    }

    public <T> void get(final ObservableEmitter<T> emitter) {
        okHttpClient.newCall(builder.url(HttpUtils.createUrlFromParams(reqUrl, param)).get().build())
                .enqueue(getCallBack(emitter));
    }


}