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
import org.apache.commons.lang3.RandomStringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class AESCBCUtils {

    public static final String KEY_ALGORITHM    = "AES";
    // 加密模式为ECB，填充模式为NoPadding
    public static final String CIPHER_ALGORITHM = "AES/CBC/NoPadding";
    // 字符集
    public static final String ENCODING         = "UTF-8";
    // 向量
    public static final String IV_SEED          = "1234567812345678";

    /**
     * AES加密算法
     * 
     * @param str 密文
     * @param key 密key
     * @return
     */
    public static String encrypt(String str, String key) {
        try {
            if (str == null) {
                log.info("AES加密出错:Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (key.length() != 16) {
                log.info("AES加密出错:Key长度不是16位");
                return null;
            }
            byte[] raw = key.getBytes(ENCODING);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            //
            IvParameterSpec iv = new IvParameterSpec(IV_SEED.getBytes(ENCODING));
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] srawt = str.getBytes(ENCODING);
            int len = srawt.length;
            /* 计算补空格后的长度 */
            while (len % 16 != 0) {
                len++;
            }
            byte[] sraw = new byte[len];
            /* 在最后空格 */
            for (int i = 0; i < len; ++i) {
                if (i < srawt.length) {
                    sraw[i] = srawt[i];
                } else {
                    sraw[i] = 32;
                }
            }
            byte[] encrypted = cipher.doFinal(sraw);
            return formatString(new String(Base64.encodeBase64(encrypted), "UTF-8"));
        } catch (Exception ex) {
            log.info("AES加密出错 ex={}", ex);
            return null;
        }
    }

    /**
     * AES解密算法
     * 
     * @param str 密文
     * @param key 密key
     * @return
     */
    public static String decrypt(String str, String key) {
        try {
            // 判断Key是否正确
            if (key == null) {
                log.info("AES解密出错:Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (key.length() != 16) {
                log.info("AES解密出错：Key长度不是16位");
                return null;
            }
            byte[] raw = key.getBytes(ENCODING);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            byte[] ivBytes = IV_SEED.getBytes(ENCODING);
            IvParameterSpec iv = new IvParameterSpec(ivBytes);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] bytes = Base64.decodeBase64(str.getBytes("UTF-8"));
            bytes = cipher.doFinal(bytes);
            return new String(bytes, ENCODING);
        } catch (Exception ex) {
            log.info("AES解密出错ex={}", ex);
            return null;
        }
    }

    private static String formatString(String sourceStr) {
        if (sourceStr == null) {
            return null;
        }
        return sourceStr.replaceAll("\\r", "").replaceAll("\\n", "");
    }

    /**
     * byte数组转化为16进制字符串
     * 
     * @param bytes
     * @return
     */
    public static String byteToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String strHex = Integer.toHexString(bytes[i]);
            if (strHex.length() > 3) {
                sb.append(strHex.substring(6));
            } else {
                if (strHex.length() < 2) {
                    sb.append("0" + strHex);
                } else {
                    sb.append(strHex);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 随机生成秘钥
     */
    public static String getAesKey() {
        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128);//要生成多少位，只需要修改这里即可128, 192或256    
            SecretKey sk = kg.generateKey();
            byte[] b = sk.getEncoded();
            String str = byteToHexString(b);
            return str;
        } catch (NoSuchAlgorithmException e) {
            log.info("getAesKey error e={}", e);
            return "defaultaes";
        }
    }

    public static void main(String[] args) {
        System.out.println(RandomStringUtils.random(16, true, true));
    }

}
