package com.example.framework.work;


import com.example.framework.work.entity.resp.FileResp;
import com.example.framework.work.entity.resp.LiveHotListResp;
import com.example.framework.work.entity.resp.LoginResp;
import com.example.framework.work.entity.resp.UserInfoResp;
import com.example.hwokhttp.NetData;

import java.net.PortUnreachableException;


public class API {

    @NetData(dataClass = UserInfoResp.class)
    public static String INFO = "http://beiqapi.powertrend.cn/customer/getMyInfo";

    @NetData(dataClass = LiveHotListResp.class)
    public static String Live = "http://live-dg-dev-001.ynyqbkj.cn/live_home_ranking";

    @NetData(dataClass = LoginResp.class)
    public static String LOGIN = "http://beiqapi.powertrend.cn/login";

    @NetData(dataClass = FileResp.class)
    public static String FILE="http://mall-dg-dev-001.ynyqbkj.cn/common/uploadSingleFile";
}
