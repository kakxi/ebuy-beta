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
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.function.Predicate;

/**
 * <P>
 * <P>
 * 
 * @author caojiayao
 * @version $Id: EnumUtil.java, v 0.1 2018年4月19日 下午6:04:51 caojiayao Exp $
 */
public final class EnumUtil {

    /**
     * <P>
     * code转换枚举
     * <P>
     * 
     * @param enumClass
     * @param code
     * @return
     */
    public static <E extends BaseEnum> E valueOf(Class<E> enumClass, String code) {
        if (enumClass != null && StringUtils.isNotBlank(code)) {
            for (E e : enumClass.getEnumConstants()) {
                if (StringUtils.equals(code, e.getCode())) {
                    return e;
                }
            }
        }
        return null;
    }

    /**
     * <P>
     * 获得code
     * <P>
     * 
     * @param baseEnum
     * @return
     */
    public static String getCode(BaseEnum baseEnum) {
        return baseEnum != null ? baseEnum.getCode() : null;
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
            @Override
            public String apply(BaseEnum input) {
                return input.getCode();
            }
        }) : null;
    }


    public static <E extends Enum<E>> E enumOf(Class<E> enumType, String name) {
        if (enumType == null || name == null) {
            return null;
        }
        try {
            return E.valueOf(enumType, name);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static <E extends Enum<E>> E enumOf(Class<E> enumType, Predicate<E> predicate) {
        if (enumType == null || predicate == null) {
            return null;
        }
        E[] enumConstants = enumType.getEnumConstants();
        for (E e : enumConstants) {
            if (predicate.test(e)) {
                return e;
            }
        }
        return null;
    }

}
