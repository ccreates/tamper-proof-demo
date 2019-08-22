/**
 * Copyright (c) 2017 ZHONGHENG, Inc. All rights reserved.
 * This software is the confidential and proprietary information of
 * ZHONGHENG, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with ZHONGHENG.
 */
package com.createsboy.api.utils;

import com.alibaba.fastjson.JSON;
import com.createsboy.api.test.domain.request.TestParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

/**
 * Des:
 * ClassName: MD5Util
 * Author: createsboy
 * Date: 2018/7/4
 * Time: 10:36
 */
public class MD5Util {
    private static final Logger LOGGER = LoggerFactory.getLogger(MD5Util.class);

    /**
     * MD5摘要
     *
     * @param value
     * @return
     */
    public static String encrytor(String value) {
        return DigestUtils.md5DigestAsHex(value.getBytes());
    }

    /**
     * MD5摘要
     *
     * @param value
     * @param salt
     * @return
     */
    public static String encrytor(String value, String salt) {
        return DigestUtils.md5DigestAsHex((value + "%/%" + salt).getBytes());
    }

    public static void main(String[] args) {
//        String encrytor0 = MD5Util.encrytor("测试0");
//        System.out.println(encrytor0);
//
//        String encrytor1 = MD5Util.encrytor("测试1", "ab");
//        System.out.println(encrytor1);

        TestParams params = new TestParams();
        params.setCode("VB147258");
        params.setName("Test");
        Long currTime = System.currentTimeMillis();
        System.out.println(currTime);
        System.out.println( MD5Util.encrytor(currTime+ JSON.toJSONString(params)));
    }
}
