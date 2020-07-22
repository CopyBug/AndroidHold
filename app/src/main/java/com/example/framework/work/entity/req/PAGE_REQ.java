package com.example.framework.work.entity.req;

import com.example.hwokhttp.BaseReqEntity;
import com.example.hwokhttp.ReqName;

public class PAGE_REQ extends BaseReqEntity {
    @ReqName("token")
    private int current=0;
    private int size=10;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
