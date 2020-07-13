package com.example.hwokhttp;

import com.google.gson.Gson;

import org.json.JSONObject;

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
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Time       : 2020/07/10 下午1:54
 * Author     : sion
 * Description:
 */
public class OkPostReq<T> extends OkReq<T> {


    public OkPostReq(OkHttpClient okHttpClient) {
        super(okHttpClient);
    }


    public <T> Observable<T> doPostFromRx() {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) {
                doPost(formBody(), emitter);
            }
        });
    }

    public <T> Observable<T> doPostJsonRx() {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) {
                doPost(jsonBody(), emitter);
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
                            emitter.onNext((T) new Gson().fromJson(json, t.getClass()));
                        } catch (IOException e) {
                            e.printStackTrace();
                            emitter.onError(new Throwable(e.getMessage() + ""));
                        }
                    }
                });
    }

    public FormBody formBody() {
        FormBody.Builder formBody = new FormBody.Builder();
        for (Map.Entry<String, String> reqParam : this.param.entrySet()) {
            formBody.add(reqParam.getKey(), reqParam.getValue());
        }
        return formBody.build();
    }

    public RequestBody jsonBody() {
        String jsonBody = new Gson().toJson(param);
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonBody);
    }

    public void multipartBody(String key,File value){
        MultipartBody.Builder multipartBody = new MultipartBody.Builder();
        for (Map.Entry<String, String> entry : param.entrySet()) {
            multipartBody.addFormDataPart(entry.getKey(),entry.getValue());
        }
//        multipartBody.addFormDataPart(key,value.getName(),)

    }
}
