package com.example.ui.view.widget.pay_ui;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.ui.R;


/**
 * Created by Laiyimin on 2017/4/20.
 */

public class PayFragment extends DialogFragment implements View.OnClickListener, PayPwdView.InputCallBack {


    private PayPwdView psw_input;
    private PayPwdView.InputCallBack inputCallBack;

    private String type = "";
    private String money = "";

    /*@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getWindow().setWindowAnimations(R.style.AnimBottom);
    }*/

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.fragment_pay);
        dialog.setCanceledOnTouchOutside(false); // 外部点击取消

        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.gravity = Gravity.BOTTOM;
        window.setAttributes(lp);

        initView(dialog);
        return dialog;
    }

    private void initView(Dialog dialog) {
        psw_input = (PayPwdView) dialog.findViewById(R.id.payPwdView);
        PwdInputMethodView inputMethodView = (PwdInputMethodView) dialog.findViewById(R.id.inputMethodView);
        psw_input.setInputMethodView(inputMethodView);
        psw_input.setInputCallBack(inputCallBack);

        ((TextView) dialog.findViewById(R.id.type_tv)).setText(type);
        ((TextView) dialog.findViewById(R.id.money_tv)).setText(money);

        dialog.findViewById(R.id.iv_close_rl).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_close_rl) {
            dismiss();
        }
    }

    /**
     * 设置输入回调
     *
     * @param inputCallBack
     */
    public void setPaySuccessCallBack(PayPwdView.InputCallBack inputCallBack) {
        this.inputCallBack = inputCallBack;
    }

    @Override
    public void onInputFinish(String result) {

    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
