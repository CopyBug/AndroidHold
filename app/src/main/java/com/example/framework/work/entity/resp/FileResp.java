package com.example.framework.work.entity.resp;

public class FileResp {

    @Override
    public String toString() {
        return "FileResp{" +
                "status=" + status +
                ", desc='" + desc + '\'' +
                ", data='" + data + '\'' +
                ", flag=" + flag +
                ", code=" + code +
                ", msg=" + msg +
                ", count=" + count +
                '}';
    }

    /**
     * status : true
     * desc : 上传成功
     * data : https://yaoni2020-1301520140.cos.ap-guangzhou.myqcloud.com/yn/159538445512686475.png
     * flag : null
     * code : null
     * msg : null
     * count : 0
     */

    private boolean status;
    private String desc;
    private String data;
    private Object flag;
    private Object code;
    private Object msg;
    private int count;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Object getFlag() {
        return flag;
    }

    public void setFlag(Object flag) {
        this.flag = flag;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
