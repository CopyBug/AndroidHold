package com.example.framework.work.until.cache;

/**
 * ACache保存key常量
 */
public class ACEConstant {
    /**
     * 保存登录令牌信息的key
     */
    public static String ACE_FTOKEN_KEY = "ACE_FTOKEN_KEY";

    /**
     * 缓存保存的时间   由于登录的token是24分钟这里默认所有的数据缓存时间为24分钟
     */
//    public static Integer ACE_CACHE_TIME =60*24*60;
    public static Integer ACE_CACHE_TIME = -1;

    /**
     * 计时
     */
    public static String ACE_DAOJISHI = "ACE_DAOJISHI";
    /**
     * 当前app保存的语言类型id
     */
    public static String CURRENTLANGUAGE_ID = "CURRENTLANGUAGE_ID";
    /**
     * 简体
     */
    public static final String LANGUAGE_SIMPLIFIED_CHINESE = "1";
    /**
     * 繁体
     */
    public static final String LANGUAGE_TRADITIONAL_CHINESE = "2";
    /**
     * 英语
     */
    public static final String LANGUAGE_ENGLISH = "3";

    /**
     * 保存用户个人信息
     */
    public static String ACE_USERINFO = "ACE_USERINFO";
    /**
     * 保存全局信息设置
     */
    public static String ACE_BASESETTING = "ACE_BASESETTING";

    /**
     * 缓存用户ID
     */
    public static String ACE_USERINFO_USERID = "userId";

    /**
     * 跳转返回值
     */
    public static final int REQUEST_OK = 1;

    //二级密码是否设置标识
    public static final String SEC_FLAG = "sec_pwd_flag";

    /**
     * 主题缓存
     */
    public static String ACE_THEMEMODE = "ACE_THEMEMODE";
}
