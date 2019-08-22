/**
 * Copyright (c) 2017 ZHONGHENG, Inc. All rights reserved.
 * This software is the confidential and proprietary information of
 * ZHONGHENG, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with ZHONGHENG.
 */
package com.createsboy.api.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Des: AES加解密工具
 * ClassName: AESUtil
 * Author: createsboy
 * Date: 2018/7/3
 * Time: 15:38
 */
public class AESUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(AESUtil.class);

    /**
     * AES加解密
     */
    private static final String ALGORITHM = "AES";

    /**
     * 向量值
     */
    private static final String IV_DEFAULT = "y2c0WWCgtPscSChG";

    /**
     * key
     */
    private static final String KEY_DEFAULT = "Ai7eMsxGDpoD81BE";

    /**
     * CBC模式
     */
    private static final String TRANSFORM_CBC_PKCS5 = "AES/CBC/PKCS5Padding";

    /**
     * AES加密（CBC模式）
     *
     * @param value 加密字符串
     * @param key   秘钥（如果为空则为默认值）
     * @param iv    初始化向量值（如果为空则为默认值）
     * @return
     */
    public static String encrypt(String value, String key, String iv) {
        if (StringUtil.isNotEmpty(value)) {
            //如果key为空，则使用默认值
            key = (key == null || key.length() != 16) ? KEY_DEFAULT : key;
            //如果iv为空，则使用默认值
            iv = (iv == null || iv.length() != 16) ? IV_DEFAULT : iv;
            //密码
            SecretKeySpec keySpec = getSecretKey(key);
            //初始化向量器
            IvParameterSpec ivParameterSpec = new IvParameterSpec(string2Bytes(iv));
            try {
                Cipher encipher = Cipher.getInstance(TRANSFORM_CBC_PKCS5);
                //加密模式
                encipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);
                //使用AES加密
                byte[] encrypted = encipher.doFinal(string2Bytes(value));
                //然后转成BASE64返回
                return Base64.encodeBase64String(encrypted);
            } catch (Exception e) {
                LOGGER.error("AESUtil", e.getStackTrace());
            }
        }
        return null;
    }

    /**
     * AES解密（CBC模式）
     *
     * @param encryptedStr 解密之后的字符串
     * @param key          秘钥（如果为空则为默认值）
     * @param iv           初始化向量值（如果为空则为默认值）
     * @return
     */
    public static String decrypt(String encryptedStr, String key, String iv) {
        if (StringUtil.isNotEmpty(encryptedStr)) {
            //如果key为空，则使用默认值
            key = (key == null || key.length() != 16) ? KEY_DEFAULT : key;
            //如果iv为空，则使用默认值
            iv = (iv == null || iv.length() != 16) ? IV_DEFAULT : iv;
            //密码
            SecretKeySpec keySpec = getSecretKey(key);
            //初始化向量器
            IvParameterSpec ivParameterSpec = new IvParameterSpec(string2Bytes(iv));
            try {
                Cipher encipher = Cipher.getInstance(TRANSFORM_CBC_PKCS5);
                //加密模式
                encipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);
                //先用BASE64解密
                byte[] encryptedBytes = Base64.decodeBase64(encryptedStr);
                //然后再AES解密
                byte[] originalBytes = encipher.doFinal(encryptedBytes);
                //返回字符串
                return new String(originalBytes);
            } catch (Exception e) {
                LOGGER.error("AESUtil", e.getStackTrace());
            }
        }
        return null;
    }

    /**
     * 将字符串转化为UTF8格式的byte数组
     *
     * @param input the input string
     * @return UTF8 bytes
     */
    private static byte[] string2Bytes(String input) {
        return input.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * 生成秘钥
     *
     * @param KEY 秘钥
     * @return
     */
    private static SecretKeySpec getSecretKey(String KEY) {
        //生成指定算法密钥
        KeyGenerator generator = null;
        try {
            generator = KeyGenerator.getInstance(ALGORITHM);
            //指定AES密钥长度（128、192、256）
            generator.init(256, new SecureRandom(string2Bytes(KEY)));
            //生成一个密钥
            SecretKey secretKey = generator.generateKey();
            //转换为AES专用密钥
            return new SecretKeySpec(secretKey.getEncoded(), ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("AESUtil", e.getStackTrace());
        }
        return null;
    }

    public static void main(String[] args) {
        String encryptedStr0 = AESUtil.encrypt("测试0", null, null);
        System.out.println(encryptedStr0);
        String originalStr0 = AESUtil.decrypt(encryptedStr0, null, null);
        System.out.println(originalStr0);

        String encryptedStr1 = AESUtil.encrypt("测试1", "wOOCgsm3kB4GPP4P", "XWQaTBHLDFCS4t1J");
        System.out.println(encryptedStr1);
        String originalStr1 = AESUtil.decrypt(encryptedStr1, "wOOCgsm3kB4GPP4P", "XWQaTBHLDFCS4t1J");
        System.out.println(originalStr1);
    }

}
