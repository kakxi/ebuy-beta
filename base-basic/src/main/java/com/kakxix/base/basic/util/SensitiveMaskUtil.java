/*
 * Copyright 2018 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.kakxix.base.basic.util;

import com.kakxix.base.basic.annotation.SensitiveInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

/**
 * ======================================================== 敏感信息脱敏工具类
 *
 * @author AngleSuper.Wei
 * @date 2016/12/12 ========================================================
 */
@Slf4j
public class SensitiveMaskUtil {

    /**
     * [身份证号] 显示最后四位，其他隐藏。共计18位或者15位。<例子：*************5762>
     *
     * @param idCard
     * @return
     */
    public static String maskIdCard(String idCard) {

        if (StringUtils.isBlank(idCard) || idCard.length() < 5) {
            return "";
        }
        String num = StringUtils.right(idCard, 4);
        return StringUtils.leftPad(num, StringUtils.length(idCard), "*");
    }

    /**
     * [银行卡号] 前六位，后四位，其他用星号隐藏每位1个星号<例子:6222600**********1234>
     *
     * @param bankCardNo
     * @return
     */
    public static String maskBankCard(String bankCardNo) {

        if (StringUtils.isBlank(bankCardNo) || bankCardNo.length() < 7) {

            return bankCardNo;
        }

        return StringUtils.left(bankCardNo, 6).concat(
                StringUtils.removeStart(
                        StringUtils.leftPad(StringUtils.right(bankCardNo, 4), StringUtils.length(bankCardNo), "*"),
                        "******"));
    }

    /**
     * [交易密码] 交易密码全部密文
     *
     * @param password
     * @return
     */
    public static String maskPassword(String password) {

        if (StringUtils.isBlank(password)) {

            return password;
        }

        return "******";
    }

    /**
     * [中文姓名] 只显示第一个汉字，其他隐藏为2个星号<例子：李**>
     *
     * @param userName
     * @return
     */
    public static String maskUserName(String userName) {

        if (StringUtils.isBlank(userName)) {
            return "";
        }
        String name = StringUtils.left(userName, 1);
        return StringUtils.rightPad(name, StringUtils.length(userName), "*");
    }

    /**
     * [中文姓名] 只显示最后一个汉字，其他隐藏为2个星号<例子：**宇>
     *
     * @param userName
     * @return
     */
    public static String maskUserNameLeft(String userName) {

        if (StringUtils.isBlank(userName)) {
            return "";
        }
        String name = StringUtils.right(userName, 1);
        return StringUtils.leftPad(name, StringUtils.length(userName), "*");
    }

    /**
     * [手机号码] 前三位，后四位，其他隐藏<例子:138******1234>
     *
     * @param num
     * @return
     */
    public static String maskMobilePhone(String num) {
        if (StringUtils.isBlank(num)) {
            return "";
        }
        return StringUtils.left(num, 3).concat(
                StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(num, 4), StringUtils.length(num), "*"),
                        "***"));
    }

    /**
     * [公司开户银行联号] 公司开户银行联行号,显示前两位，其他用星号隐藏，每位1个星号<例子:12********>
     *
     * @param code
     * @return
     */
    public static String maskCNAPSCode(String code) {
        if (StringUtils.isBlank(code)) {
            return "";
        }
        return StringUtils.rightPad(StringUtils.left(code, 2), StringUtils.length(code), "*");
    }

    /**
     * [地址] 只显示到地区，不显示详细地址；我们要对个人信息增强保护<例子：北京市海淀区****>
     *
     * @param address
     * @param sensitiveSize 敏感信息长度
     * @return
     */
    public static String maskAddress(String address, int sensitiveSize) {
        if (StringUtils.isBlank(address)) {
            return "";
        }
        int length = StringUtils.length(address);
        return StringUtils.rightPad(StringUtils.left(address, length - sensitiveSize), length, "*");
    }

    /**
     * [电子邮箱] 邮箱前缀仅显示第一个字母，前缀其他隐藏，用星号代替，@及后面的地址显示<例子:g**@163.com>
     *
     * @param email
     * @return
     */
    public static String maskEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return "";
        }
        int index = StringUtils.indexOf(email, "@");
        if (index <= 1) {
            return email;
        } else {
            return StringUtils.rightPad(StringUtils.left(email, 1), index, "*").concat(
                    StringUtils.mid(email, index, StringUtils.length(email)));
        }
    }

    private static Field[] findAllField(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        return fields;
    }

    private static void replace(Field[] fields, Object javaBean, Set<Integer> referenceCounter)
            throws IllegalArgumentException, IllegalAccessException {
        if (null != fields && fields.length > 0) {
            for (Field field : fields) {
                field.setAccessible(true);
                if (null != field && null != javaBean) {
                    Object value = field.get(javaBean);
                    if (null != value) {
                        Class<?> type = value.getClass();
                        // 1.处理子属性，包括集合中的
                        if (type.isArray()) {
                            int len = Array.getLength(value);
                            for (int i = 0; i < len; i++) {
                                Object arrayObject = Array.get(value, i);
                                SensitiveMaskUtil.replace(SensitiveMaskUtil.findAllField(arrayObject.getClass()),
                                        arrayObject, referenceCounter);
                            }
                        } else if (value instanceof Collection<?>) {
                            Collection<?> c = (Collection<?>) value;
                            Iterator<?> it = c.iterator();
                            while (it.hasNext()) {
                                Object collectionObj = it.next();
                                SensitiveMaskUtil.replace(SensitiveMaskUtil.findAllField(collectionObj.getClass()),
                                        collectionObj, referenceCounter);
                            }
                        } else if (value instanceof Map<?, ?>) {
                            Map<?, ?> m = (Map<?, ?>) value;
                            Set<?> set = m.entrySet();
                            for (Object o : set) {
                                Map.Entry<?, ?> entry = (Map.Entry<?, ?>) o;
                                Object mapVal = entry.getValue();
                                SensitiveMaskUtil.replace(SensitiveMaskUtil.findAllField(mapVal.getClass()), mapVal,
                                        referenceCounter);
                            }
                        } else if (!type.isPrimitive()
                                && !StringUtils.startsWith(type.getPackage().getName(), "javax.")
                                && !StringUtils.startsWith(type.getPackage().getName(), "java.")
                                && !StringUtils.startsWith(field.getType().getName(), "javax.")
                                && !StringUtils.startsWith(field.getName(), "java.")
                                && referenceCounter.add(value.hashCode())) {
                            SensitiveMaskUtil.replace(SensitiveMaskUtil.findAllField(type), value, referenceCounter);
                        }
                    }

                    // 2. 处理自身的属性
                    SensitiveInfo annotation = field.getAnnotation(SensitiveInfo.class);
                    if (field.getType().equals(String.class) && null != annotation) {
                        String valueStr = (String) value;
                        if (StringUtils.isNotBlank(valueStr)) {
                            switch (annotation.type()) {
                                case CHINESE_NAME: {
                                    field.set(javaBean, SensitiveMaskUtil.maskUserName(valueStr));
                                    break;
                                }
                                case ID_CARD: {
                                    field.set(javaBean, SensitiveMaskUtil.maskIdCard(valueStr));
                                    break;
                                }
                                case MOBILE_PHONE: {
                                    field.set(javaBean, SensitiveMaskUtil.maskMobilePhone(valueStr));
                                    break;
                                }
                                case EMAIL: {
                                    field.set(javaBean, SensitiveMaskUtil.maskEmail(valueStr));
                                    break;
                                }
                                case BANK_CARD: {
                                    field.set(javaBean, SensitiveMaskUtil.maskBankCard(valueStr));
                                    break;
                                }
                                case CNAPS_CODE: {
                                    field.set(javaBean, SensitiveMaskUtil.maskCNAPSCode(valueStr));
                                    break;
                                }
                                case DETAIL: {
                                    field.set(javaBean, "******");
                                    break;
                                }
                                case ADDRESS: {
                                    field.set(javaBean, SensitiveMaskUtil.maskAddress(valueStr, 10));
                                    break;
                                }
                                case PASSWORD: {
                                    field.set(javaBean, SensitiveMaskUtil.maskPassword(valueStr));
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
