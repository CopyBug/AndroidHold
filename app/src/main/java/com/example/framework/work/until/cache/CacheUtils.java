package com.example.framework.work.until.cache;

import android.content.Context;

import com.example.framework.app.MyApp;


/**
 * 缓存操作工具类
 */
public class CacheUtils {
    /**
     * 获取登录令牌
     *
     * @param context
     * @param flag 是否跳转
     */
    public static FToken getToken(Context context, boolean flag) {
        FToken token = (FToken) ACache.get(context).getAsObject(ACEConstant.ACE_FTOKEN_KEY);
        if (token == null ||isNull(token.token)) {
            if (flag) {
//                context.startActivity(new Intent(context, LoginAct.class));
            }
        }
        return token;
    }

    /**
     * 登录成功后保存的Token
     *
     * @param context
     * @param token
     */
    public static void saveToken(Context context, FToken token) {
        ACache.get(context).put(ACEConstant.ACE_FTOKEN_KEY, token);
    }


    /**
     * 保存登录用户信息
     *
     * @param context
     * @param entity
     * @return
     */
    public static boolean saveUserInfo(Context context, UserInfoEntity entity) {
        try {
            ACache.get(context).put(ACEConstant.ACE_USERINFO, entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //保存用户是否设置二级密码标识
    public static boolean saveSecPwdFlag(Context context, String sec_flag) {
        try {
            ACache.get(context).put(ACEConstant.SEC_FLAG, sec_flag);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //返回用户是否设置二级密码标识
    public static String getSecPwdFlag(Context context) {
        try {
            return ACache.get(context).getAsString(ACEConstant.SEC_FLAG);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取用户信息，若不存在则返回null
     *
     * @param context
     * @return
     */
    public static UserInfoEntity getUserInfo(Context context) {
        try {
            return (UserInfoEntity) ACache.get(context).getAsObject(ACEConstant.ACE_USERINFO);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 判断当前用户是否登录
     */
    public static Boolean checkUserInfo(Context context, boolean flag) {
        try {
            FToken fToken = getToken(context, flag);
            if (fToken != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 退出登录
     *
     * @param context
     */
    public static void logout(Context context) {
        ACache.get(context).remove(ACEConstant.ACE_USERINFO);
        ACache.get(context).remove(ACEConstant.ACE_FTOKEN_KEY);
    }


    /**
     * 获取缓存的语言
     *
     * @param context
     * @return
     */
    public static String getAppLanguage(Context context) {
        String language_id = ACache.get(context).getAsString(ACEConstant.CURRENTLANGUAGE_ID);
        if (isNull(language_id)) {
            language_id = ACEConstant.LANGUAGE_SIMPLIFIED_CHINESE;
            ACache.get(context).put(ACEConstant.CURRENTLANGUAGE_ID, language_id);
        }
        return language_id;
    }


    /**
     * 获取缓存的语言
     *
     * @return
     */
    public static String getAppLanguageApi() {
        try {
            String language_id = ACache.get(MyApp.getApplication()).getAsString(ACEConstant.CURRENTLANGUAGE_ID);
            if (isNull(language_id)) {
                return "zh_cn";
            }
            switch (language_id) {
                case ACEConstant.LANGUAGE_SIMPLIFIED_CHINESE:
                default:
                    return "zh_cn";
                case ACEConstant.LANGUAGE_TRADITIONAL_CHINESE:
                    return "zh_tw";
                case ACEConstant.LANGUAGE_ENGLISH:
                    return "en";
            }
        } catch (Exception e) {
            return "zh_cn";
        }
    }

    public static boolean isNull(String value){
        return value==null||"".equals(value);
    }

}
