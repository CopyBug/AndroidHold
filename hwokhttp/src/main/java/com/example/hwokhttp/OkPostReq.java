package com.example.hwokhttp;

import com.google.gson.Gson;

import java.io.File;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

/**
 * Time       : 2020/07/10 下午1:54
 * Author     : sion
 * Description:
 */
@SuppressWarnings("TypeParameterHidesVisibleType")
public class OkPostReq<T> extends BaseOkReq<T> {


    public OkPostReq(OkHttpClient okHttpClient, String reqUrl) {
        super(okHttpClient, reqUrl);
    }

    @Override
    public <T> Observable<T> doPostFromRx() {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) {
                doPost(formBody(), emitter);
            }
        });
    }

    @Override
    public <T> Observable<T> doPostJsonRx() {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) {
                doPost(jsonBody(), emitter);
            }
        });
    }

    @Override
    public <T> Observable<T> doPost() {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) {
                doPost(RequestBody.create(null, ""), emitter);
            }
        });
    }

    @Override
    public <T> Observable<T> doPostFile() {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) {
                doPost(multipartBody(), emitter);

            }
        });
    }

    protected <T> void doPost(RequestBody reqBody, final ObservableEmitter<T> emitter) {
        okHttpClient.newCall(builder.url(HttpUtils.createUrlFromParams(reqUrl, param)).post(reqBody).build())
                .enqueue((getCallBack(emitter)));
    }

    public FormBody formBody() {
        FormBody.Builder formBody = new FormBody.Builder();
        for (Map.Entry<String, Object> reqParam : this.param.entrySet()) {
            formBody.add(reqParam.getKey(), reqParam.getValue().toString());
        }
        return formBody.build();
    }

    public RequestBody jsonBody() {
        String jsonBody = new Gson().toJson(param);
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonBody);
    }


    public MultipartBody multipartBody() {
        MultipartBody.Builder multipartBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            if (entry.getValue() instanceof File) {
                File r = (File) entry.getValue();
                multipartBody.addFormDataPart(entry.getKey(), r.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), r));
            } else {
                multipartBody.addFormDataPart(entry.getKey(), entry.getValue().toString());
            }
        }
        return multipartBody.build();
    }
}
