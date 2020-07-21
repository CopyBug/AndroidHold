package com.example.framework.work.entity.req;

import com.example.hwokhttp.BaseReqEntity;
import com.example.hwokhttp.ReqName;

public class USER_INFO_REQ extends BaseReqEntity {
    @ReqName("token")
    public String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
