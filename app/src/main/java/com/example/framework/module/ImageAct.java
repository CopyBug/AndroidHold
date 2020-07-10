package com.example.framework.module;

import com.example.framework.R;
import com.example.framework.databinding.ActImagesBinding;
import com.sjz20200427.base.BaseActivity;

public class ImageAct extends BaseActivity<ActImagesBinding> {

    private final String IMAGE_URL = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1594040184515&di=b7806535386ef077df89a95bb3243d51&imgtype=0&src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fphotoblog%2F1606%2F11%2Fc9%2F22672172_1465635465244.jpg";

    @Override
    public boolean isDatabing() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_images;
    }


    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        binding.setImageUrl(IMAGE_URL);
    }

    @Override
    protected void doBusiness() {

    }
}
