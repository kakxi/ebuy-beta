package com.kakxix.base.basic.dto;

import com.kakxix.base.basic.util.JsonUtil;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

public class BaseDto implements Serializable {

    private static final long serialVersionUID = 4911893826506129759L;

    @Override
    public String toString() {
        return JsonUtil.toJsonString(this);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return SerializationUtils.clone(this);
    }

    public static String toString(Object object) {
        return JsonUtil.toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return JsonUtil.parse(json, clazz);
    }
}
