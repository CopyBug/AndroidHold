package com.example.hwokhttp;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Time       : 2020/07/10 下午1:54
 * Author     : sion
 * Description:
 */
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
                post(emitter);
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

    protected <T> void post(final ObservableEmitter<T> emitter) {
        okHttpClient.newCall(builder.url(reqUrl).post(null).build())
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        emitter.onError(e);
                        emitter.onComplete();
                    }

                    @Override
                    public void onResponse(Call call, Response response) {
                        try {
                            String json = response.body().string();
                            emitter.onNext((T) new Gson().fromJson(json,getClass()));
                            emitter.onComplete();
                        } catch (IOException e) {
                            e.printStackTrace();
                            emitter.onError(new Throwable(e.getMessage() + ""));
                            emitter.onComplete();
                        }
                    }
                });
    }

    protected <T> void doPost(RequestBody reqBody, final ObservableEmitter<T> emitter) {
        okHttpClient.newCall(builder.post(reqBody).build())
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Throwable throwable = new Throwable(e.getMessage() + "");
                        emitter.onError(throwable);
                        emitter.onComplete();
                    }

                    @Override
                    public void onResponse(Call call, Response response) {
                        try {
                            String json = response.body().string();
                            emitter.onNext((T) new Gson().fromJson(json, getClass()));
                        } catch (IOException e) {
                            e.printStackTrace();
                            emitter.onError(new Throwable(e.getMessage() + ""));
                        }
                    }
                });
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
            if(entry.getValue() instanceof File){
                File r= (File) entry.getValue();
                multipartBody.addFormDataPart(entry.getKey(), r.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), r));
            }else{
                multipartBody.addFormDataPart(entry.getKey(), entry.getValue().toString());
            }
        }
        return multipartBody.build();
    }
}
