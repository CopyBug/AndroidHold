package com.example.hwokhttp;

import android.os.Build;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
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
public class OkGetReq<T> extends BaseOkReq<T> {

    Class<T> classZ;
    public OkGetReq(OkHttpClient okHttpClient, String reqUrl) {
        super(okHttpClient, reqUrl);
        classZ= GenericsUtils.getSuperClassGenricType(OkGetReq.class,0);
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
                                JSONObject jsonObject = new JSONObject(json);
                                if(jsonObject.getBoolean("status")){
                                    T fromJson = (T) new Gson().fromJson(json,classZ.getClass());
                                    Log.i("==",fromJson.toString());
                                    emitter.onNext(fromJson);
                                }else{
                                    emitter.onError(new Throwable(jsonObject.getString("desc")));
                                    emitter.onComplete();
                                }
                        } catch (Exception e) {
                            e.printStackTrace();
                            emitter.onError(new Throwable(e.getMessage() + ""));
                            emitter.onComplete();
                        }
                    }
                });
    }


}