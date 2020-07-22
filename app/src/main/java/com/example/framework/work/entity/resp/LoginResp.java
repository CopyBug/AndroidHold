package com.example.framework.work.entity.resp;

public class LoginResp {

    /**
     * data : Mv0Jw9B8WxEOJ89cj*4See4mVZQKy33Q
     * desc : 登陆成功
     * flag :
     * status : true
     */

    private String data;
    private String desc;
    private String flag;
    private boolean status;

    @Override
    public String toString() {
        return "LoginResp{" +
                "data='" + data + '\'' +
                ", desc='" + desc + '\'' +
                ", flag='" + flag + '\'' +
                ", status=" + status +
                '}';
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
