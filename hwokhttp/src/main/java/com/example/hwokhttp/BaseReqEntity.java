package com.example.hwokhttp;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BaseReqEntity {
    protected Map<String,Object> reqMap;

    public BaseReqEntity() {
        this.reqMap = new HashMap<>();
    }

    public Map<String, Object> getReqMap() {
        Class<? extends BaseReqEntity> aClass = this.getClass();
        Field[] fields = aClass.getFields();
        for (Field field : fields) {
            field.setAccessible(true);
            ReqName reqName = field.getAnnotation(ReqName.class);
            if(reqName!=null){
                String name = reqName.value();
                try {
                    reqMap.put(name,field.get(this));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return reqMap;
    }
}
