package com.example.hwokhttp;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Time       : 2020/07/10 上午11:19
 * Author     : sion
 * Description:
 */
public class HwNetManager {
    private static HwNetManager hwNetManager;
    private static Map<String,Class<?>> classMap;
    public static HwNetManager getHwNetManager() {
        if(hwNetManager==null){
            synchronized (HwNetManager.class){
                if(hwNetManager==null){
                    hwNetManager=new HwNetManager();
                }
            }
        }
        return hwNetManager;
    }

    public Class<?> getClass(String name){
       return classMap.get(name);
    }

    public HwNetManager(){
        classMap=new LinkedHashMap<>();
    }
    public void initApi(Class apiClass){
        try {
            Object newInstance = apiClass.newInstance();
            Class<?> aClass = Class.forName(apiClass.getName());
            Field[] fields = aClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                NetData annotation = field.getAnnotation(NetData.class);
                if(annotation!=null){
                    classMap.put(field.get(newInstance).toString(),annotation.dataClass());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
