/*
 * Copyright 2018 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.kakxix.base.basic.util;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.kakxix.base.basic.enums.BaseEnum;
import lombok.experimental.UtilityClass;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * 类BaseEnumUtil.java的实现描述：枚举工具类
 *
 */
@UtilityClass
public class BaseEnumUtil {

    /**
     * 判断当前枚举变量的code是否为指定的值
     *
     * @param code
     * @param e
     * @return
     */
    public static boolean codeIs(String code, BaseEnum e) {
        return e.getCode().equals(code);
    }

    /**
     * 根据指定的code和Enum类型，获取Message，若转换失败则返回""
     *
     * @param code
     * @param type
     * @return
     */
    public static <E extends BaseEnum> String getMessage(String code, Class<E> type) {
        E e = tryParse(code, type);
        return e == null ? "" : e.getMessage();
    }

    /**
     * 根据指定的code和Enum类型，判断code是否在该枚举的有效范围内
     *
     * @param code
     * @param type
     * @return
     */
    public static <E extends BaseEnum> boolean isValid(String code, Class<E> type) {
        return tryParse(code, type) != null;
    }

    /**
     * 根据指定的code和Enum类型，转换成Enum实例，若转换失败抛出异常
     *
     * @param code
     * @param type
     * @return
     */
    public static <E extends BaseEnum> E parse(String code, Class<E> type) {
        E value = tryParse(code, type);
        if (value == null) {
            throw new IllegalArgumentException("Cannot convert " + code + " to " + type.getName() + " by code value.");
        }
        return value;
    }

    /**
     * 根据指定的code和Enum类型，转换成Enum实例，若转换失败则返回null
     *
     * @param code
     * @param type
     * @return
     */
    public static <E extends BaseEnum> E tryParse(String code, Class<E> type) {
        E[] enums = type.getEnumConstants();
        for (E item : enums) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }

    /**
     * <P>
     * 获得code 集合
     * <P>
     * 
     * @param baseEnums
     * @return
     */
    public static <T extends BaseEnum> List<String> getCodeList(List<T> baseEnums) {
        return CollectionUtils.isNotEmpty(baseEnums) ? Lists.transform(baseEnums, new Function<BaseEnum, String>() {
            public String apply(BaseEnum input) {
                return input.getCode();
            }
        }) : null;
    }
}
