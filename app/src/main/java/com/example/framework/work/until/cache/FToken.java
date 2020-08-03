package com.example.framework.work.until.cache;


import java.io.Serializable;

/**
 * Created by lzj on 2017/11/15.
 */

public class FToken implements Serializable {

    /**
     * "key": "175aca235baf0356b4ad80a6e9b8e29d",
     * "token": "uspjclp99hm4jtf31f6ph05q13",
     * uid": "2"
     */
    public String getVerify_key() {
        return verify_key;
    }

    public void setVerify_key(String verify_key) {
        this.verify_key = verify_key;
    }

    private String verify_key;
    private String uid;
    private String key;
    public String token;

}
