/*
 * Copyright 2018 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.kakxix.base.basic.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Base64Util
 */
@Slf4j
public class Base64Util {

    private static char Base64Code[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9', '+', '/' };

    /**
     * 将文件转成base64 字符串
     * 
     * @param file 文件
     * @return *
     * @throws Exception
     */
    public static String encodeBase64File(File file) throws Exception {
        InputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[inputFile.available()];
        IOUtils.read(inputFile, buffer);
        inputFile.close();
        return encode(buffer);
    }

    public static String encode(String code) {
        return encodeByString(code.getBytes());
    }

    public static String encodeByString(byte b[]) {
        int code = 0;
        StringBuffer sb = new StringBuffer((b.length - 1) / 3 << 6);
        for (int i = 0; i < b.length; i++) {
            code |= b[i] << 16 - (i % 3) * 8 & 255 << 16 - (i % 3) * 8;
            if (i % 3 == 2 || i == b.length - 1) {
                sb.append(Base64Code[(code & 0xfc0000) >>> 18]);
                sb.append(Base64Code[(code & 0x3f000) >>> 12]);
                sb.append(Base64Code[(code & 0xfc0) >>> 6]);
                sb.append(Base64Code[code & 0x3f]);
                code = 0;
            }
        }

        if (b.length % 3 > 0) {
            sb.setCharAt(sb.length() - 1, '=');
        }
        if (b.length % 3 == 1) {
            sb.setCharAt(sb.length() - 2, '=');
        }
        return sb.toString();
    }

    /**
     * 将二进制数据编码为BASE64字符串
     * 
     * @param binaryData
     * @return
     */
    public static String encode(byte[] binaryData) {
        try {
            return new String(Base64.encodeBase64(binaryData));
        } catch (Exception e) {
            log.warn("unsupportedEncodingException,error={}", e);
            return null;
        }
    }

    /**
     * 将BASE64字符串恢复为二进制数据
     * 
     * @param base64String
     * @return
     */
    public static byte[] decodeWithOutHead(String base64String) {
        //base64str 头部"data:image/jpeg;base64,"不进行decode
        try {
            return Base64.decodeBase64(base64String.split(",")[1].getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.warn("unsupportedEncodingException,error={}", e);
            return null;
        }
    }

    /**
     * 将BASE64字符串恢复为二进制数据
     * 
     * @param base64String
     * @return
     */
    public static byte[] decode(String base64String) {
        try {
            return Base64.decodeBase64(base64String.getBytes());
        } catch (Exception e) {
            log.warn("unsupportedEncodingException,error={}", e);
            return null;
        }
    }

    /**
     * getFileType
     * 
     * @param base64String
     * @return
     */
    public static String getFileType(String base64String) {
        try {
            return base64String.split(";")[0].split("/")[1];
        } catch (Exception e) {
            log.warn("exception,error={}", e);
            return null;
        }
    }
}
