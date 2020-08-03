package com.example.framework;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.framework.module.ImageAct;
import com.example.framework.module.ImageOpAct;
import com.example.framework.module.NetOpAct;
import com.example.framework.work.until.cache.CacheUtils;
import com.example.framework.work.until.cache.FToken;
import com.example.ui.view.widget.CountdownView;
import com.example.ui.view.widget.pay_ui.PayFragment;
import com.example.ui.view.widget.pay_ui.PayPwdView;
import com.sjz20200427.base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button main_image_show;
    private Button main_image_op;
    private Button main_net;
    private Button main_pay;
    private Button main_cache;
    private EditText edit_cache;
    private TextView tv_cache;
    private CountdownView cv_find_countdown;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        main_image_show = (Button) findViewById(R.id.main_image_show);
        edit_cache = (EditText) findViewById(R.id.edit_cache);
        main_net = (Button) findViewById(R.id.main_net);
        main_cache = (Button) findViewById(R.id.main_cache);
        main_pay = (Button) findViewById(R.id.main_pay);
        main_image_op = (Button) findViewById(R.id.main_image_op);
        cv_find_countdown = findViewById(R.id.cv_find_countdown);
        tv_cache = findViewById(R.id.tv_cache);
        main_image_show.setOnClickListener(this);
        main_image_op.setOnClickListener(this);
        main_pay.setOnClickListener(this);
        main_net.setOnClickListener(this);
        main_cache.setOnClickListener(this);
        cv_find_countdown.setOnClickListener(this);
        if(CacheUtils.getToken(this,false)!=null){
            tv_cache.setText(CacheUtils.getToken(this,false).token);

        }
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void doBusiness() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_image_show:
                //图片的显示
                startActivity(ImageAct.class);
                break;
            case R.id.main_image_op:
                //图片的操作处理
                startActivity(ImageOpAct.class);
                break;
            case R.id.main_net:
                //网络请求框架
                startActivity(NetOpAct.class);
                break;
            case R.id.cv_find_countdown:
                cv_find_countdown.start();
                break;
            case R.id.main_pay:
                PayFragment payFragment = new PayFragment();
                payFragment.setMoney(String.valueOf(100));
                payFragment.setPaySuccessCallBack(new PayPwdView.InputCallBack() {
                    @Override
                    public void onInputFinish(String result) {
                        Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                        payFragment.dismiss();
                    }
                });
                payFragment.show(getSupportFragmentManager(),"pay");
                break;
            case R.id.main_cache:
                //缓存
                String cache = edit_cache.getText().toString();
                FToken fToken = new FToken();
                fToken.token=cache;
                CacheUtils.saveToken(this,fToken);
                tv_cache.setText(CacheUtils.getToken(this,false).token);
                break;
        }
    }
}
