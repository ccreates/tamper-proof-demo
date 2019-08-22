/**
 * Copyright (c) 2017 ZHONGHENG, Inc. All rights reserved.
 * This software is the confidential and proprietary information of
 * ZHONGHENG, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with ZHONGHENG.
 */
package com.createsboy.api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Des:
 * ClassName: Base64Util
 * Author: createsboy
 * Date: 2018/7/3
 * Time: 16:24
 */
public class Base64Util extends Base64Utils {
    private static final Logger LOGGER = LoggerFactory.getLogger(Base64Util.class);

    /**
     * 编码格式
     */
    private static final String CODE = "UTF-8";

    /**
     * Base64加密
     *
     * @param value
     * @return
     */
    public static String encrytor(String value) {
        if (StringUtil.isEmpty(value)) {
            return null;
        } else {
            try {
                return new BASE64Encoder().encode(value.getBytes(CODE));
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("Base64Util", e.getStackTrace());
            }
        }
        return null;
    }

    /**
     * Base64加密
     *
     * @param value
     * @return
     */
    public static String encrytor(byte[] value) {
        if (StringUtil.isEmpty(value)) {
            return null;
        } else {
            return new BASE64Encoder().encode(value);
        }
    }

    /**
     * Base64解密
     *
     * @param value
     * @return
     */
    public static byte[] decryptor(String value) {
        if (StringUtil.isEmpty(value)) {
            return null;
        } else {
            try {
                return new BASE64Decoder().decodeBuffer(value);
            } catch (IOException e) {
                LOGGER.error("Base64Util", e.getStackTrace());
            }
        }
        return null;
    }

    /**
     * Base64解密
     *
     * @param value
     * @return
     */
    public static String decryptorStr(String value) {
        if (StringUtil.isEmpty(value)) {
            return null;
        } else {
            try {
                return new String(new BASE64Decoder().decodeBuffer(value), CODE);
            } catch (IOException e) {
                LOGGER.error("Base64Util", e.getStackTrace());
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String encrytor = Base64Util.encrytor("测试0");
        System.out.println(encrytor);

        String decryptorStr = Base64Util.decryptorStr(encrytor);
        System.out.println(decryptorStr);
    }
}
