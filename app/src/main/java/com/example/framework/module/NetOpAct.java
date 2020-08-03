package com.example.framework.module;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.strictmode.DiskWriteViolation;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.framework.R;
import com.example.framework.work.API;
import com.example.framework.work.entity.req.FILE_REQ;
import com.example.framework.work.entity.req.LOGIN_REQ;
import com.example.framework.work.entity.req.PAGE_REQ;
import com.example.framework.work.entity.req.USER_INFO_REQ;
import com.example.framework.work.entity.resp.FileResp;
import com.example.framework.work.entity.resp.LiveHotListResp;
import com.example.framework.work.entity.resp.LoginResp;
import com.example.framework.work.entity.resp.UserInfoResp;
import com.example.hwokhttp.NetData;
import com.example.hwokhttp.OkMode;
import com.example.hwokhttp.OkRun;
import com.example.ui.view.dialog.DialogWaiting;
import com.hjq.image.ImageLoader;
import com.hjq.image.imageFun.HWImageManager;
import com.hjq.image.imageFun.ImgUtil;
import com.sjz20200427.base.BaseActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;

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


    @Override
    public void onClick(View view) {
        tv_result.setText("");
        switch (view.getId()) {
            case R.id.main_net_get:
                doGet();
                break;
            case R.id.main_net_post:
                doPost();
                break;
            case R.id.main_net_post_form:

                break;
            case R.id.main_net_post_body:
                doPostBody();
                break;
            case R.id.main_net_post_file:
                RxPermissions rxPermissions = new RxPermissions(this);
                rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                if (aBoolean) {
                                    HWImageManager.getInstance().getBitmapFun().imageOpenPhoto(NetOpAct.this, 1022);
                                } else {
                                    Toast.makeText(NetOpAct.this, "请打开权限", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != -1) {
            return;
        }
        if (requestCode == 1022) {
            if (data != null && data.getData() != null) {
                Uri uri = data.getData();
                File file = new File(ImgUtil.getPath(this, uri));
                FILE_REQ file_req = new FILE_REQ();
                file_req.img = file;
                doPostFile(file_req);
            }
        }
    }

    private void doPostBody() {
        PAGE_REQ page_req = new PAGE_REQ();
        new OkRun.Builder()
                .api(API.Live)
                .setBaseReqEntity(page_req)
                .paramMode(OkMode.POST_FORM)
                .<LiveHotListResp>doRun()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LiveHotListResp>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LiveHotListResp liveHotListResp) {

                        for (LiveHotListResp.DataBean datum : liveHotListResp.getData()) {
                            tv_result.append(datum.toString());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(NetOpAct.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void doGet() {
        USER_INFO_REQ info_req = new USER_INFO_REQ();
        info_req.token = "dmr1Uy3ahuJCSSVJVFnwXufew*zQMOHm";
        new OkRun.Builder()
                .api(API.INFO)
                .setBaseReqEntity(info_req)
                .paramMode(OkMode.GET)
                .<UserInfoResp>doRun().doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) {
                DialogWaiting.getInstance().showDialog(NetOpAct.this, false, "加载中");
            }
        }).subscribe(new Observer<UserInfoResp>() {
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
                DialogWaiting.getInstance().hideDialog();
            }
        });
    }

    private void doPost() {
        LOGIN_REQ login_req = new LOGIN_REQ();
        login_req.channel = "1";
        login_req.login = "15220676878";
        login_req.password = "mmm1113333";
        login_req.type = "3";
        new OkRun.Builder()
                .api(API.LOGIN)
                .setBaseReqEntity(login_req)
                .paramMode(OkMode.POST)
                .<LoginResp>doRun().doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) {

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginResp>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginResp loginResp) {
                        tv_result.setText(loginResp.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(NetOpAct.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void doPostFile(FILE_REQ file_req) {
        new OkRun.Builder()
                .api(API.FILE)
                .setBaseReqEntity(file_req)
                .paramMode(OkMode.POST_FILE)
                .<FileResp>doRun()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FileResp>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FileResp fileResp) {
                        tv_result.setText(fileResp.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(NetOpAct.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
