package com.example.framework.module;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.framework.R;
import com.example.framework.work.entity.req.USER_INFO_REQ;
import com.example.framework.work.entity.resp.UserInfoResp;
import com.example.hwokhttp.OkMode;
import com.example.hwokhttp.OkRun;
import com.sjz20200427.base.BaseActivity;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class NetOpAct extends BaseActivity implements View.OnClickListener {
    private Button main_net_get;
    private Button main_net_post;
    private Button main_net_post_form;
    private Button main_net_post_body;
    private Button main_net_post_file;
    private TextView tv_result;

    @Override
    protected int getLayoutId() {
        return R.layout.act_net;
    }

    @Override
    protected void initView() {
        main_net_get = findViewById(R.id.main_net_get);
        main_net_post = findViewById(R.id.main_net_post);
        main_net_post_form = findViewById(R.id.main_net_post_form);
        main_net_post_body = findViewById(R.id.main_net_post_body);
        main_net_post_file = findViewById(R.id.main_net_post_file);
        tv_result = findViewById(R.id.tv_result);
        main_net_get.setOnClickListener(this);
        main_net_post.setOnClickListener(this);
        main_net_post_form.setOnClickListener(this);
        main_net_post_body.setOnClickListener(this);
        main_net_post_file.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void doBusiness() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private String API = "http://beiqapi.powertrend.cn/customer/getMyInfo";

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_net_get:
                //http://beiqapi.powertrend.cn/customer/getMyInfo?token=dmr1Uy3ahuLWiveYcXU*SdIIzBqDQN*S
                USER_INFO_REQ info_req = new USER_INFO_REQ();
                info_req.token = "dmr1Uy3ahuJCSSVJVFnwXufew*zQMOHm";
                new OkRun.Builder<UserInfoResp>().api(API).setBaseReqEntity(info_req)
                        .paramMode(OkMode.GET)
                        .doRun().doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<UserInfoResp>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(UserInfoResp userInfoResp) {
                                tv_result.setText(userInfoResp.toString());
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(NetOpAct.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
            case R.id.main_net_post:
                break;
            case R.id.main_net_post_form:
                break;
            case R.id.main_net_post_body:
                break;
            case R.id.main_net_post_file:
                break;
        }
    }
}
