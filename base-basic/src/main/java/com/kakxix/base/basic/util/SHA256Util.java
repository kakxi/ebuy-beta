/*
 * Copyright 2018 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.kakxix.base.basic.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

/**
 * 类SHA256Util.java的实现描述：SHA256Util
 * 
 * @author lijianglong 2018年6月4日 下午2:14:12
 */
@Slf4j
@UtilityClass
public class SHA256Util {

    /**
     * 利用Apache的工具类实现SHA-256加密
     * 
     * @param str 加密后的报文
     * @return
     */
    public static String encode(String str) {
        MessageDigest messageDigest;
        String encodedStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
            encodedStr = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            log.error("SHA256Util.encode NoSuchAlgorithmException ", e);
        } catch (UnsupportedEncodingException e) {
            log.error("SHA256Util.encode UnsupportedEncodingException ", e);
        }
        return encodedStr;
    }

    /**
     * 对摘要串做默克尔树加密
     * 
     * @param strTree 加密后的报文
     * @return
     */
    public static String merkleTreeEncode(final TreeMap<String, String> strTree) {
        String encodedStr = null;
        for (String val : strTree.values()) {
            encodedStr = StringUtils.isBlank(encodedStr) ? val : encode(String.format("%s%s", encodedStr, val));
        }
        return encodedStr;
    }
}
