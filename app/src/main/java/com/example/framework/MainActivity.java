package com.example.framework;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.framework.module.ImageAct;
import com.example.framework.module.ImageOpAct;
import com.sjz20200427.base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button main_image_show;
    private Button main_image_op;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        main_image_show = (Button) findViewById(R.id.main_image_show);
        main_image_op = (Button) findViewById(R.id.main_image_op);
        main_image_show.setOnClickListener(this);
        main_image_op.setOnClickListener(this);
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
        }
    }
}
