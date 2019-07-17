/*
 * Copyright 2018 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.kakxix.base.basic.util;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

/**
 * MD5工具类
 */
@Slf4j
public class MD5Utils {

    private static final char[] HEX = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
            'f' };

    public static final String encode(String source) {
        try {
            byte[] sourceBytes = source.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(sourceBytes);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                str[k++] = HEX[md[i] >>> 4 & 0xf];
                str[k++] = HEX[md[i] & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            log.error("MD5Util.encode error,source is {}", source, e);
            return null;
        }
    }

    public static final boolean isValid(String encPass, String rawPass) {
        if (encPass.equals(rawPass)) {
            return true;
        }
        return MD5Utils.encode(rawPass).equals(encPass);
    }
}
